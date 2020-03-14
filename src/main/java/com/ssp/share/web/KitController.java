package com.ssp.share.web;


import com.ssp.share.pojo.Category;
import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.User;
import com.ssp.share.service.CategoryService;
import com.ssp.share.service.KitImageService;
import com.ssp.share.service.KitService;
import com.ssp.share.util.Page4Navigator;
import com.ssp.share.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.List;


@RestController
public class KitController {

    @Autowired
    KitService kitService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    KitImageService kitImageService;

    @GetMapping("/kitcategories/{cid}/kits")
    public Page4Navigator<Kit> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                    HttpSession session) {
        start = start<0?0:start;
        User user = (User) session.getAttribute("user");
        Page4Navigator<Kit> page = kitService.list(cid, user,start, size,5 );
        kitImageService.setFirstKitImages(page.getContent());
        return page;
    }


    /*
    后台抽取验证状态数据
     */
    @GetMapping("/adminkitcategories/{cid}/kits")
    public Page4Navigator<Kit> listUnVerity(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start,
                                    @RequestParam(value = "size", defaultValue = "10") int size,
                                            @RequestParam(value = "icon") String icon
                                    ) {
        start = start<0?0:start;
        Page4Navigator<Kit> page = kitService.listUnVerity(cid, icon,start, size,5 );
        kitImageService.setFirstKitImages(page.getContent());
        return page;
    }

    @GetMapping("/kits")
    public List<Kit> listUnVerify(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String icon = "未审核";
        List<Kit> lists = kitService.getUnVerity(icon,user);
        return lists;
    }

    @GetMapping("/kits/{kid}")
    public Kit get(@PathVariable("kid") int kid) {
        Kit bean = kitService.get(kid);
        return bean;
    }

    @PostMapping("/kits")
    public Object add(@RequestParam("file") MultipartFile file, @RequestParam("cid") int cid,
                      HttpServletRequest request, HttpSession session) throws IOException {
        Kit bean = new Kit();
        bean.setName(request.getParameter("name"));  //设置名称
        bean.setDescription(request.getParameter("description"));  //描述信息
        bean.setDownloadTimes(0);  //设置下载次数
        bean.setFlag(request.getParameter("flag"));  //设置状态
        bean.setCreateTime(new Date());  //设置创建时间
        bean.setUpdateTime(new Date());  //设置更新时间
        bean.setIcon("未审核");
        Category category = categoryService.get(cid);
        User user = (User) session.getAttribute("user");
        bean.setUser(user);
        bean.setCategory(category);   //设置归属分类

        //获取文件的原始名称
        String fileName = file.getOriginalFilename();
        //创建项目存放路径并重新命名文件
        String destFileName = request.getServletContext().getRealPath("") + "uploaded" +  File.separator + System.currentTimeMillis() + fileName;
        //创建目标文件目录
        File destFile = new File(destFileName);
        if(!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        //传输文件
        file.transferTo(destFile);
        bean.setAddress(destFileName);  //设置工具存放地址
        kitService.add(bean);
        return bean;
    }

    @DeleteMapping("/kits/{id}")
    public String delete(@PathVariable("id") int id,HttpServletRequest request) {
    //删除本类下工具 同时删除 关联文件
        //通过前台传递过来的id拿到工具对象
        Kit bean = kitService.get(id);
        //切割地址字符串 拿到尾部文件名
        String[] str = bean.getAddress().split("\\\\");  //  \\要经过转义
        String keyword = "";
        for(int i=0; i<str.length; i++) {
            if(i == str.length-1) {
                keyword = str[i];
            }
        }

        //遍历放置上传文件的文件夹，拿到包含该字符串的文件之后将文件本身其删除
        File fileFolder = new File(request.getServletContext().getRealPath("/uploaded/"));
        //拿到文件夹下的文件
        File[] files = fileFolder.listFiles();
        if(files != null) {              //此处可以做优化
            //遍历文件夹下的文件
            for(int i=0; i<files.length; i++) {
                //切割文件名
                String[] sp = files[i].getName().split("\\\\");
                for (String ss: sp) {
                    //判断尾部名与关键字是否有确定关系，如果有就删除这个文件对象file[i]
                    if(ss.contains(keyword)) {
                        files[i].delete();
                        System.out.println("删除成功！");
                    }
                    else{
                        System.out.println("删除失败，上传文件中不包含关键字文件！");
                    }
                }
            }
        }
        else{
            System.out.println("files数组为空！");
        }
        kitService.delete(id);
        return null;
    }

    @PutMapping("/kits")
    public Object update(@RequestParam("file") MultipartFile file,
                         HttpServletRequest request) throws IOException {
        Kit bean = kitService.get(Integer.parseInt(request.getParameter("id")));
        bean.setName(request.getParameter("name"));
        bean.setDescription(request.getParameter("description"));  //设置简介
        bean.setFlag(request.getParameter("flag"));  //设置状态
        bean.setUpdateTime(new Date());  //创建时间
        System.out.println("------------");
        System.out.println(bean.toString());

        //如果前台上传的文件为空或者没上传操作 直接保存更新数据 不改动
        if(file == null) {
            kitService.update(bean);
            return Result.success();
        }
        //获取现上传的文件的原始名称
        String fileName = file.getOriginalFilename();
        //创建上传文件的存放路径并重新命名上传文件
        String destFileName = request.getServletContext().getRealPath("") + "uploaded" + File.separator + System.currentTimeMillis() + fileName;
        //创建该上传文件的 目标文件目录
        File destFile = new File(destFileName);
        if(!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        //查询文件夹中的旧文件 并将其删
        //获取原工具的存储地址，拿到工具名称
        String[] str = bean.getAddress().split("\\\\");  //  \\要经过转义
        String keyword = "";
        for(int i=0; i<str.length; i++) {
            if(i == str.length-1) {
                keyword = str[i];
            }
        }
        //遍历放置上传文件的文件夹，拿到包含该字符串的文件之后将文件本身其删除
        File fileFolder = new File(request.getServletContext().getRealPath("/uploaded/"));
        //拿到文件夹下的文件
        File[] files = fileFolder.listFiles();
        if(files != null) {              //此处可以做优化
            //遍历文件夹下的文件
            for(int i=0; i<files.length; i++) {
                //切割文件名
                String[] sp = files[i].getName().split("\\\\");
                for (String ss: sp) {
                    //判断尾部名与关键字是否有确定关系，如果有就删除这个文件对象file[i]
                    if(ss.contains(keyword)) {
                        files[i].delete();
                        System.out.println("删除成功！");
                        //再传输文件
                        file.transferTo(destFile);
                        //设置上传后的工具存放地址
                        bean.setAddress(destFileName);
                        kitService.update(bean);
                    }
                    else{
                        System.out.println("删除旧文件失败，上传文件中不包含关键字文件！");
                    }
                }
            }
        }
        else{
            System.out.println("files数组为空！源文件夹中没有文件!");
        }
        return Result.success();
    }
}
