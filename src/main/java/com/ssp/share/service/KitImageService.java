package com.ssp.share.service;


import com.ssp.share.dao.KitImageDAO;
import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.KitImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="kitImages")
public class KitImageService {

    //提供了两个常量，分别表示单个图片和详情图片：
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Autowired
    KitImageDAO kitImageDAO;
    @Autowired
    KitService kitService;

    @CacheEvict(allEntries=true)
    public void add(KitImage bean) {
        kitImageDAO.save(bean);
    }

    @CacheEvict(allEntries=true)
    public void delete(int id) {
        kitImageDAO.delete(id);
    }

    @Cacheable(key="'kitImages-one-'+ #p0")
    public KitImage get(int id) {
        return kitImageDAO.findOne(id);
    }

    //提供了根据产品id和图片类型查询的list方法
    @Cacheable(key="'kitImages-single-kid-'+ #p0.id")
    public List<KitImage> listSingleKitImages(Kit kit) {
        return kitImageDAO.findByKitAndTypeOrderByIdDesc(kit, type_single);
    }

    @Cacheable(key="'kitImages-detail-kid-'+ #p0.id")
    public List<KitImage> listDetailKitImages(Kit kit) {
        return kitImageDAO.findByKitAndTypeOrderByIdDesc(kit, type_detail);
    }

    public void setFirstKitImage(Kit kit) {
        List<KitImage> singleImages = listSingleKitImages(kit);
        if(!singleImages.isEmpty())
            kit.setFirstKitImage(singleImages.get(0));
        else
            kit.setFirstKitImage(new KitImage());

    }
    public void setFirstKitImages(List<Kit> kits) {
        for (Kit kit : kits) {
            setFirstKitImage(kit);
        }
    }
}
