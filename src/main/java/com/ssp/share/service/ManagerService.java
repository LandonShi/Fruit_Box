package com.ssp.share.service;


import com.ssp.share.dao.ManagerDAO;
import com.ssp.share.pojo.Manager;
import com.ssp.share.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames ="managers" )
public class ManagerService {

    @Autowired
    ManagerDAO managerDAO;


    /*
        检验登录
     */
    @Cacheable(key="'managers-one-name-'+ #p0 +'-password-'+ #p1")
    public Manager checkLogin(String name, String password) {
        Manager manager = managerDAO.findByNameAndPassword(name, password);
        return manager;
    }

    /*
       管理员 关键字查询
     */
    @Cacheable(key="'managers-page-'+#p0")
    public List<Manager> list(String keyword) {
        List<Manager> list = managerDAO.listByManager(keyword);
        return list;
    }

    @Cacheable(key="'managers-page-'+#p0+ '-' + #p1")
    public Page4Navigator<Manager> list(int start, int size, int navigatePages,String flag) {
        Sort sort;
        if(flag.equals("1")) {  //1  1降序排列
             sort = new Sort(Sort.Direction.DESC, "id");
        }else {                //2   0升序排列
             sort = new Sort(Sort.Direction.ASC,"id");
        }
        Pageable pageable = new PageRequest(start, size,sort);
        System.out.println("---------------------");
        System.out.println(pageable.toString());
        Page pageFromJPA = managerDAO.findAll(pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    @CacheEvict(allEntries=true)
    public void addManager(Manager manager) {
        managerDAO.save(manager);
    }
}
