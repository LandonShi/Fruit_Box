package com.ssp.share.web;


import com.ssp.share.pojo.*;
import com.ssp.share.service.*;
import com.ssp.share.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminRESTController {

    @Autowired
    UserService userService;
    @Autowired
    ManagerService managerService;
    @Autowired
    KitService kitService;
    @Autowired
    KitImageService kitImageService;
    @Autowired
    PropertyValueService propertyValueService;

    /*
     * 用户后台登录
     */
    @PostMapping("/adminLogin")
    public Object login(@RequestBody User userParam, HttpSession session) {
        String name =  userParam.getUsername();
        name = HtmlUtils.htmlEscape(name);
        String password = userParam.getPassword();
        User user =userService.checkUser(name,password);
        if(null==user){
            String message ="账号密码错误";
            return Result.fail(message);
        }
        else{
            session.setAttribute("user", user);
            return Result.success();
        }
    }

    /*
    管理员登录
     */
    @PostMapping("/managerLogin")
    public Object login(@RequestBody Manager manager,HttpSession session) {
        Manager m = managerService.checkLogin(manager.getName(), manager.getPassword());
        if(m == null) {
            return Result.fail("账号不存在");
        }
        session.setAttribute("manager", m);
        return Result.success();
    }
    /*
    后台 管理员关键字查询
     */
    @GetMapping("/managersearch")
    public Object list(String keyword) {
        if(keyword == null) {
            keyword = "";
        }
        List<Manager> list = managerService.list(keyword);
        return list;
    }

    /*
     后台用户关键字查询
     */
    @PostMapping("userssearch")
    public Object search( String keyword){
        if(null==keyword)
            keyword = "";
        List<User> list= userService.search(keyword);
        return list;
    }


    /*
    审核数据
     */
    @GetMapping("/adminkit/{kid}")
    public Object kit(@PathVariable("kid") int kid) {
        Kit kit = kitService.get(kid);
        List<KitImage> kitSingleImages = kitImageService.listSingleKitImages(kit);
        List<KitImage> kitDetailImages = kitImageService.listDetailKitImages(kit);
        kit.setKitSingleImages(kitSingleImages);
        kit.setKitDetailImages(kitDetailImages);

        List<PropertyValue> pvs = propertyValueService.list(kit);
        kitImageService.setFirstKitImage(kit);

        Map<String,Object> map = new HashMap<>();
        map.put("kit", kit);
        map.put("pvs", pvs);
        return Result.success(map);
    }

    /*
    审核通过
     */
    @PutMapping("/adminkits/{kid}")
    public Object kitPass(@PathVariable("kid") int kid) {
        String icon = "已审核";
        kitService.updateKitPass(icon,kid);
        return Result.success();
    }

}
