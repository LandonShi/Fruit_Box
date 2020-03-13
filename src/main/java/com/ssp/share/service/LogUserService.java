package com.ssp.share.service;


import com.ssp.share.dao.LogUserDAO;

import com.ssp.share.pojo.LogUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogUserService {

    @Autowired
    LogUserDAO logUserDAO;


    public void add(LogUser logUser) {
        logUserDAO.save(logUser);
    }

    public List<LogUser> list(int uid) {
        List<LogUser> list = logUserDAO.listFour(uid);
        return list;
    }

    public  List<LogUser> checkLoginIp(int uid) {
        return logUserDAO.listTow(uid);
    }
}
