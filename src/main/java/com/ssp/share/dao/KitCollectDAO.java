package com.ssp.share.dao;

import com.ssp.share.pojo.KitCollect;
import com.ssp.share.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface KitCollectDAO extends JpaRepository<KitCollect, Integer> {

    /*
    判断是否重复添加工具
     */
    @Query(value = "select * from kitcollect where kid = ?1 and uid = ?2",nativeQuery = true)
    KitCollect checkSame(int kid, int uid);

    List<KitCollect> findByUser(User user);
}
