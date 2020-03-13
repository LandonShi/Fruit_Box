package com.ssp.share.web;


import com.ssp.share.pojo.Manager;
import com.ssp.share.service.ManagerService;
import com.ssp.share.util.Page4Navigator;
import com.ssp.share.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class ManagerController {


    @Autowired
    ManagerService managerService;

    @GetMapping("/managers")
    public Page4Navigator<Manager> listManager(@RequestParam(value = "start", defaultValue = "0") int start,
                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                               @RequestParam(value = "flag" ) String flag ) {
        start = start<0?0:start;
        Page4Navigator<Manager> page = managerService.list(start,size,5,flag);
        return page;
    }

    @PostMapping("/managers")
    public Object addManager(@RequestBody Manager manager) {
        managerService.addManager(manager);
        return Result.success();
    }
}
