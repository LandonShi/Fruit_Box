package com.ssp.share.service;

import com.ssp.share.dao.ReviewDAO;
import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="reviews")
public class ReviewService {

    @Autowired
    ReviewDAO reviewDAO;
    @Autowired KitService kitService;

    @CacheEvict(allEntries=true)
    public void add(Review review) {
        reviewDAO.save(review);
    }

    @Cacheable(key="'reviews-kid-'+ #p0.id")
    public List<Review> list(Kit kit){
        List<Review> result =  reviewDAO.findByKitOrderByIdDesc(kit);
        return result;
    }

    @Cacheable(key="'reviews-count-kid-'+ #p0.id")
    public int getCount(Kit kit) {
        return reviewDAO.countByKit(kit);
    }


}
