package com.ssp.share.web;


import com.ssp.share.dao.KitCollectDAO;
import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.KitCollect;
import com.ssp.share.pojo.User;
import com.ssp.share.service.KitCollectService;
import com.ssp.share.service.KitService;
import com.ssp.share.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KitCollectController {

    @Autowired
    KitCollectDAO kitCollectDAO;
    @Autowired
    KitService kitService;
    @Autowired
    KitCollectService kitCollectService;

    @PostMapping("/collects/{kid}")
    public Object addCollect(HttpSession session, @PathVariable("kid") int kid) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return Result.fail("您未登录！");
        }
        KitCollect ss = (KitCollect) kitCollectService.checkHaveSameKit(kid, user.getId());
        if(ss != null){
            return Result.fail("该工具已经在您的收藏夹中！如需取消，请移步个人中心。");
        }else {
            KitCollect kitCollect = new KitCollect();
            Kit kit = kitService.get(kid);
            kitCollect.setUser(user);
            kitCollect.setKit(kit);
            kitCollectDAO.save(kitCollect);
            return Result.success();
        }
    }

    @GetMapping("/collects/{kid}")
    public Object checkHasKit(HttpSession session, @PathVariable("kid") int kid) {
        User user = (User) session.getAttribute("user");
        if(user == null) {
            return Result.fail("您未登录！");
        }
        KitCollect ss = (KitCollect) kitCollectService.checkHaveSameKit(kid, user.getId());
        if(ss != null) {
            return Result.fail("该工具已经在您的收藏夹中！如需取消，请移步个人中心。");
        }
        return Result.success();
    }

    @GetMapping("/collects")
    public List<Kit> listByUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<KitCollect> list = kitCollectService.listByUser(user);
        List<Kit> kits = new ArrayList<>();
        for(int i=0;i<list.size();i++) {
            kits.add(i,list.get(i).getKit());
        }
        return kits;
    }
}
