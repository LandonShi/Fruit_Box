package com.ssp.share.dao;

import com.ssp.share.pojo.LogUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LogUserDAO extends JpaRepository<LogUser, Integer> {


    @Query(value = "select * from loguser where uid = ?1 order by loguser.loginDate DESC LIMIT 0,4",nativeQuery=true)
    List<LogUser> listFour(int uid);

    @Query(value = "select loguser.ip from loguser where uid = ?1 order by loguser.loginDate DESC limit 0,2", nativeQuery = true)
    List<LogUser> listTow(int uid);
 }
