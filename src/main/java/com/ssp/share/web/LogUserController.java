package com.ssp.share.web;


import com.ssp.share.pojo.LogUser;
import com.ssp.share.pojo.User;
import com.ssp.share.service.LogUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class LogUserController {

    @Autowired
    LogUserService logUserService;


    @GetMapping("/logusers")
    public List<LogUser> list(HttpSession session) {
        User user = (User) session.getAttribute("user");
        int uid = user.getId();
        List<LogUser> list = logUserService.list(uid);
        return list;
    }

}
