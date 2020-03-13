package com.ssp.share.dao;

import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.KitImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KitImageDAO extends JpaRepository<KitImage,Integer> {
    List<KitImage> findByKitAndTypeOrderByIdDesc(Kit kit, String type);
}