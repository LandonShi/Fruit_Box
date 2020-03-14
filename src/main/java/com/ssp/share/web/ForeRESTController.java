package com.ssp.share.web;


import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssp.share.comparator.KitAllCompare;
import com.ssp.share.comparator.KitDateCompare;
import com.ssp.share.comparator.KitDownloadTimesCompare;
import com.ssp.share.pojo.*;
import com.ssp.share.service.*;
import com.ssp.share.util.ImageUtil;
import com.ssp.share.util.RedisUtil;
import com.ssp.share.util.UserLogInfoUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.ssp.share.util.Result;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.HtmlUtils;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

@RestController
public class ForeRESTController {

    @Autowired
    UserService userService;
    @Autowired
    KitService kitService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    KitImageService kitImageService;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    LogUserService logUserService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    PropertyService propertyService;

    /*
     * 前台登录
     */
    @PostMapping("/foreLogin")
    public Object foreLogin(@RequestBody User userParam,HttpSession session,Model model,HttpServletRequest request) {
        String name =  userParam.getUsername();
        name = HtmlUtils.htmlEscape(name);

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, userParam.getPassword());
        token.setRememberMe(true);
        try {
            subject.login(token);
            User user = userService.getByName(name);

            //最近登录
            String deviceType = UserLogInfoUtil.getDeviceType(request);
            Date loginDate = UserLogInfoUtil.getDate();
            String ip = UserLogInfoUtil.getIP(request);
            String typeLogin = UserLogInfoUtil.getTypeLogin();

            LogUser logUser = new LogUser();
            logUser.setLoginDate(loginDate);
            logUser.setIp(ip);
            logUser.setTypeLogin(typeLogin);
            logUser.setDeviceType(deviceType);
            logUser.setUser(user);

            logUserService.add(logUser);
            session.setAttribute("user", user);
            model.addAttribute("user",user);
            return Result.success();
        } catch (AuthenticationException e) {
            String message ="账号密码错误";
            return Result.fail(message);
        }
    }

    @PostMapping("/index")
    public Object defaultIndex(HttpSession session ,Model model) {
    	User user = (User) session.getAttribute("user");
    	String nickname = user.getNickname();
    	String avatar = user.getAvatar();
    	model.addAttribute("nickname", nickname);
    	model.addAttribute("avatar", avatar);
    	return model;
    }

    /*
        注册
     */
    @PostMapping("/foreRegister")
    public Object register(MultipartFile image,HttpServletRequest request) throws IOException {
        User user = new User();
        user.setNickname(request.getParameter("nickname"));
        user.setEmail(request.getParameter("email"));
        user.setStatue(request.getParameter("statue"));
        String username =  request.getParameter("username");
        String password = request.getParameter("password");
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        boolean exist = userService.isExist(username);
        if(exist){
            String message ="用户名已经被使用,不能使用";
            return Result.fail(message);
        }
    // 对密码处理   开始
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";
        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
    // 对密码处理   结束
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        user.setCreateTime(new Date());
        userService.add(user);
        System.out.println("----------");
        System.out.println(user.toString());

        saveOrUpdateImageFile(user, image, request);
        File imageFolder = new File(request.getServletContext().getRealPath("img/avatar"));
        File file = new File(imageFolder,user.getId()+".jpg");
        String avatar = file.toString();
        userService.updateAvatar(avatar,user.getId());
        return Result.success();
    }

    public void saveOrUpdateImageFile(User user, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/avatar"));
        File file = new File(imageFolder,user.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    /*
        前台修改密码
     */
    @PutMapping("/updatePassword")
    public String updatePassword(@RequestParam(value = "password") String password, HttpSession session) {
        User user = (User) session.getAttribute("user");
        int uid = user.getId();
        if(user == null) {
            return "未登录";
        } else {
            //生成随机盐
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            int times = 2;
            //使用md5加密2次原密码
            String algorithmName = "md5";
            String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
            //保存进数据库
            user.setSalt(salt);
            user.setPassword(encodedPassword);
            userService.update(encodedPassword,uid);
            //清空shrio缓存
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
            Cache<Object, AuthenticationInfo> cache;
            //移除session 强制用户下线
            session.removeAttribute("user");
            return  "检测到您的密码已经被修改，即将退出，请重新登录。";
        }

    }

    /*
     * 展示工具集
     */
    @GetMapping("/forekit")
    public Object home() {
        List<Category> cs= categoryService.list();
        kitService.fill(cs);  //填充分类
        kitService.fillByRow(cs);  //填充分类下的推荐工具
        categoryService.removeCategoryFromKit(cs);
        return cs;
    }

    @GetMapping("/forekit/{kid}")
    public Object kit(@PathVariable("kid") int kid) {
        Kit kit = kitService.get(kid);
        List<KitImage> kitSingleImages = kitImageService.listSingleKitImages(kit);
        List<KitImage> kitDetailImages = kitImageService.listDetailKitImages(kit);
        kit.setKitSingleImages(kitSingleImages);
        kit.setKitDetailImages(kitDetailImages);

        List<PropertyValue> pvs = propertyValueService.list(kit);
        List<Review> reviews = reviewService.list(kit);
        kitService.setReviewNumber(kit);
        kitImageService.setFirstKitImage(kit);

        Map<String,Object> map = new HashMap<>();
        map.put("kit", kit);
        map.put("pvs", pvs);
        map.put("reviews", reviews);
        return Result.success(map);
    }

    /*
        排行榜
     */
    @GetMapping("/forekitlist/{kid}")
    public Object listSort(@PathVariable("kid") int kid,String sort ) {
        Kit kit = kitService.get(kid);
        Category category = kit.getCategory();
        kitService.fill(category);  //给这个category对象填充工具
        kitService.setReviewNumber(category.getKits());  //给工具填充评论数据
        categoryService.removeCategoryFromKit(category);
        if(null != sort) {
            switch (sort) {
                case "downloadTimes":    //人气
                    Collections.sort(category.getKits(), new KitDownloadTimesCompare());
                    break;
                case "updateTime":     //更新时间
                    Collections.sort(category.getKits(), new KitDateCompare());
                    break;
                case "all":   //综合
                    Collections.sort(category.getKits(), new KitAllCompare());
                    break;
            }
        }
        return category;
    }

    /*
      文件下载
     */
    @GetMapping("/downloads/{kid}")
    public Kit downlands(@PathVariable("kid") int kid, HttpServletResponse response,HttpServletRequest request) throws IOException {
        Kit bean = kitService.get(kid);
        //获得上传的文件名
        String address = bean.getAddress();
        String fileName = null;
        String[] str = address.split("\\\\");
        for (int i = 0; i < str.length; i++) {
            if (i == str.length - 1) {
                fileName = str[i];
            }
        }

        String path = "/uploaded";
        File filePath = new File(request.getServletContext().getRealPath(path));
        System.out.println("-----------------");
        File fileFolder = new File(filePath, fileName);
        System.out.println(fileFolder.toString());
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(fileFolder);
            bis = new BufferedInputStream(fis);
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            System.out.println("--------------------");
            System.out.println("下载成功！");
            os.flush();
        } catch (FileNotFoundException e) {
            System.out.println("下载失败！");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("下载失败！");
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bean;
    }

    /*
        搜索
     */

    @PostMapping("/forekitsearch")
    public Object search(String keyword) {
        if(keyword == null) {
            keyword = "";
        }
        List<Kit> kits = kitService.search(keyword,0,20);
        kitImageService.setFirstKitImages(kits);
        return kits;
    }

    /*
        检测用户是否登录
     */
    @GetMapping("/foreCheckLogin")
    public Object checkLogin() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            return Result.success();
        else
            return Result.fail("您还未登录，请先登录");
    }

    /*
    评论
     */

    @PostMapping("/foreReview")
    public String review(HttpSession session,String content, int kid) {
        User user = (User) session.getAttribute("user");
        content = HtmlUtils.htmlEscape(content);
        if(user != null) {
            Review review = new Review();
            review.setUser(user);
            review.setContent(content);
            review.setCreateDate(new Date());
            review.setKit(kitService.get(kid));
            reviewService.add(review);
            return "评论成功";
        } else {
            return "评论失败，未登录";
        }
    }

    /*
    个人主页获取用户对象
     */

    @GetMapping("/getUser")
    public Object getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return "未登录";
        }
        return user;
    }

    /*
    邮件验证
     */
    @Value("${spring.mail.username}")
    private String from;
    @GetMapping("/emailSend")
    public Object emailSend(HttpSession session,@RequestParam("orderEmail") String orderEmail) throws MessagingException {
        User user = (User) session.getAttribute("user");
        int uid = user.getId();
        MimeMessage message = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(orderEmail);  //收件人
        helper.setSubject("果函网订阅服务");  //主题
        helper.setText("【您正在完成果函网订阅服务】<br/><a href='http://localhost:8080/share/toTuser?uid="+ uid +"' >点击此处</a>"
                + "完成邮箱验证", true);  //内容
        helper.setFrom(from);   //发件人
        mailSender.send(message);
        userService.updateTemp(orderEmail,uid);
        return Result.success();
    }

    /*
       检查是否异地登录
     */
    public Object checkLoginRecord(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int id = user.getId();
        List<LogUser> lists = logUserService.checkLoginIp(id);
        String str1 = "";
        String str2 = "";
        return  null;
    }

    /*
     更新邮箱
     */

    @PutMapping("/email")   //这个other只是为了标识到这个映射 无其他意义
    public Object updateNewEmail( @RequestParam("newEmail") String newEmail,HttpSession session) {
        User user = (User) session.getAttribute("user");
        if(user != null) {
            userService.updateEmail(newEmail,user.getId());
            return Result.success();
        } else {
            return Result.fail("用户未登录");
        }
    }

    /*
    分类列表
     */
    @GetMapping("/forrGetCategory")
    public List<Category> listCategory() {
        List<Category> list = categoryService.list();
        return list;
    }

    /*
     获取单个分类
     */
    @GetMapping("/forGetCategory/{cid}")
    public Category get(@PathVariable("cid") int cid) {
        Category category = categoryService.get(cid);
        return category;
    }

    /*
     通过工具id获取分类
     */
    @GetMapping("/foreKits/{kid}")
    public List<Property> getPropertyByCid(@PathVariable("kid") int kid) {
        Kit kit = kitService.get(kid);
        int cid = kit.getCategory().getId();
        List<Property> list = propertyService.listByCategory(categoryService.get(cid));
        return list;
    }




}
