package com.ssp.share.dao;


import com.ssp.share.pojo.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ManagerDAO extends JpaRepository<Manager, Integer> {
    Manager findByNameAndPassword(String name, String password);


    @Query(value = "select * from manager where name like %?1%",nativeQuery=true)
    List<Manager> listByManager(String keyword);
}
