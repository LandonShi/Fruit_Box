package com.ssp.share.dao;


import com.ssp.share.pojo.Category;
import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KitDAO extends JpaRepository<Kit, Integer> {

    Page<Kit> findByCategoryAndUser(Category category, User user,Pageable pageable);
    Page<Kit> findByCategoryAndIcon(Category category, String icon,Pageable pageable);
    List<Kit> findByCategoryAndIconOrderById(Category category,String icon);   //通过分类查询所有工具
    List<Kit> findByNameLike(String keyword, Pageable pageable);
    List<Kit> findByIconAndUser(String icon, User user);

    @Modifying
    @Query(value="update kit set icon = ?1 where id = ?2",nativeQuery = true)
    void updateKitIcon(String icon, int kid);

}
