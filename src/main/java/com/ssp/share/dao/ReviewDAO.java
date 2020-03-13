package com.ssp.share.dao;

import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewDAO extends JpaRepository<Review,Integer> {

    List<Review> findByKitOrderByIdDesc(Kit kit);  //返回工具对应的评价集合
    int countByKit(Kit kit);  //返回评论数量


}
