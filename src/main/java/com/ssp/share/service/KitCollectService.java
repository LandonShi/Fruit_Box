package com.ssp.share.service;


import com.ssp.share.dao.KitCollectDAO;
import com.ssp.share.pojo.KitCollect;
import com.ssp.share.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "kitCollects")
public class KitCollectService {

    @Autowired
    KitCollectDAO kitCollectDAO;

    @Cacheable(key="'kitCollects-one-'+#p0")
    public List<KitCollect> list() {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        return kitCollectDAO.findAll(sort);
    }

    @Cacheable(key="'kitCollects-one-'+#p0")
    public List<KitCollect> listByUser(User user) {
        return kitCollectDAO.findByUser(user);
    }

    @Cacheable(key="'kitCollects-one-'+#p0")
    public KitCollect get(int id) {
        return kitCollectDAO.getOne(id);
    }

    @CacheEvict(allEntries = true)
    public void delete( int id) {
        kitCollectDAO.delete(id);
    }

    @CacheEvict(allEntries = true)
    public void add(KitCollect kitCollect) {
        kitCollectDAO.save(kitCollect);
    }

    /*
      判断是是否重新添加工具
     */
    public Object checkHaveSameKit(int kid,int uid) {
        KitCollect kitCollect = kitCollectDAO.checkSame(kid, uid);
        return  kitCollect;
    }

}
