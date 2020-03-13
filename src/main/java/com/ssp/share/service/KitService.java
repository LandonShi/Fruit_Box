package com.ssp.share.service;


import com.ssp.share.dao.KitDAO;
import com.ssp.share.pojo.Category;
import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.User;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@CacheConfig(cacheNames="kits")
public class KitService {

    @Autowired KitDAO kitDAO;
    @Autowired CategoryService categoryService;
    @Autowired KitImageService kitImageService;
    @Autowired ReviewService reviewService;

    @CacheEvict(allEntries=true)
    public void add(Kit bean) {
        kitDAO.save(bean);
    }

    @CacheEvict(allEntries=true)
    public void delete(int id) {
        kitDAO.delete(id);
    }

    @Cacheable(key="'products-one-'+ #p0")
    public Kit get(int id) {
        return kitDAO.findOne(id);
    }

    @CacheEvict(allEntries=true)
    public void update(Kit bean) {
        kitDAO.save(bean);
    }

    /*
    审核通过
     */
    @CacheEvict(allEntries=true)
    @Transactional
    public void updateKitPass(String icon, int kid) {
        kitDAO.updateKitIcon(icon,kid);
    }


    @Cacheable(key="'kits-cid-'+#p0+'-page-'+#p1 + '-' + #p2 ")
    public Page4Navigator<Kit> list(int cid,User user,int start, int size, int navigatePages) {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = kitDAO.findByCategoryAndUser(category,user,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    @Cacheable(key="'kits-icon-'+#p0+'-page-'+#p1 + '-' + #p2 ")
    public Page4Navigator<Kit> listUnVerity(int cid,String icon,int start, int size, int navigatePages) {
        Category category = categoryService.get(cid);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        Page pageFromJPA = kitDAO.findByCategoryAndIcon(category,icon,pageable);
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }

    public List<Kit> getUnVerity(String icon, User user) {
        return kitDAO.findByIconAndUser(icon,user);
    }

    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }
    }

    public void fill(Category category) {
        List<Kit> kits = listByCategory(category);
        kitImageService.setFirstKitImages(kits);
        category.setKits(kits);
    }

    public void fillByRow(List<Category> categorys) {
        int kitNumberEachRow = 8;  //横向展示默认为8个超链接
        for (Category category : categorys) {
            List<Kit> kits =  category.getKits();  //从分类集合中取出一个个分类，通过分类获取kit对象
            List<List<Kit>> kitsByRow =  new ArrayList<>();
            for (int i = 0; i < kits.size(); i += kitNumberEachRow) {
                int size = i + kitNumberEachRow;  //如果所取出的总工具个数大于8个。那么就按8个为一组切割
                size = size> kits.size()?kits.size():size;
                List<Kit> kitsOfEachRow = kits.subList(i, size);
                kitsByRow.add(kitsOfEachRow);
            }
            category.setKitsByRow(kitsByRow);
        }
    }

    @Cacheable(key="'kits-cid-'+ #p0.id")
    public List<Kit> listByCategory(Category category){
        String icon = "已审核";
        return kitDAO.findByCategoryAndIconOrderById(category,icon);
    }

    public void setReviewNumber(Kit kit) {
        int reviewCount = reviewService.getCount(kit);
        kit.setReviewCount(reviewCount);

    }

    public void setReviewNumber(List<Kit> kits) {
        for (Kit kit : kits)
            setReviewNumber(kit);
    }

    public List<Kit> search(String keyword, int start, int size) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(start, size, sort);
        List<Kit> kits = kitDAO.findByNameLike("%"+keyword+"%",pageable);
        return kits;
    }
}
