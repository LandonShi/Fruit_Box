package com.ssp.share.web;


import com.ssp.share.pojo.User;
import com.ssp.share.service.KitCollectService;
import com.ssp.share.service.ReviewService;
import com.ssp.share.service.UserService;
import com.ssp.share.util.ImageUtil;
import com.ssp.share.util.Page4Navigator;
import com.ssp.share.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@RestController
public class UserController {


    @Autowired
    UserService userService;
    @Autowired
    ReviewService reviewService;
    @Autowired
    KitCollectService kitCollectService;


    @GetMapping("/users")
    public Page4Navigator<User> listUser(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "10") int size,
                                         @RequestParam(value = "flag" ) String flag) {
        start = start<0?0:start;
        Page4Navigator<User> page = userService.list(start,size,5,flag);
        return page;
    }

    @GetMapping("/users/{uid}")
    public Object getUser(@PathVariable("uid") int uid,HttpSession session) {
        User user = userService.get(uid);
        if(user == null) {
            return Result.fail("订阅失败，未查询到用户");
        }
        //session 为空判断 防止session过期
        User u = (User) session.getAttribute("user");
        if(u == null) {
            session.setAttribute("user", user);
        }
        //更新用户新邮箱
        //到这里表明已经验证成功，将存放在临时字段里的邮箱复制到email字段里
        String email = user.getTemp();
        userService.updateEmail(email,uid);
        //激活邮箱字段
        userService.updateEmailStatue("激活", uid);
        return Result.success();
    }

    @DeleteMapping("/users/{id}")
    public String deleteBean(@PathVariable("id") int id, HttpServletRequest request) {
        User user = userService.get(id);
        //先删除用户头像
        String folder = "/img/avatar";
        File avatarFolder = new File(request.getServletContext().getRealPath(folder));
        File file = new File(avatarFolder,user.getId()+".jpg");
        file.delete();
        //接触用户的 评论
        userService.delete(id);
        return "";
    }

    @PutMapping("/users")
    public Object updateUserNickname(HttpSession session,@RequestParam("nickname") String nickname) {
        User user = (User) session.getAttribute("user");
        int uid = user.getId();
        userService.updateNickname(nickname,user.getId());
        User user1 = userService.get(uid);
        System.out.println(user1);
        if(user1.getNickname().equals(nickname)) {
            return Result.success();
        }else {
            return Result.fail("更新失败，请重新写入");
        }
    }

    @PutMapping("/users/{flag}")   //这个flag只是为了标识到这个映射 无其他意义
    public Object updateUserAvatar(@PathVariable("flag") int flag, HttpSession session,
                                   @RequestParam("file")MultipartFile image,
                                   HttpServletRequest request) throws IOException {
        User bean = (User) session.getAttribute("user");
        if(image != null) {
            saveOrUpdateImageFile(bean, image, request);
            return Result.success();
        }
        return Result.fail("更新失败，请联系管理员");
    }

    public void saveOrUpdateImageFile(User bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/avatar"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }
}
