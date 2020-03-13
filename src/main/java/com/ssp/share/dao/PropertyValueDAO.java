package com.ssp.share.dao;

import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.Property;
import com.ssp.share.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer> {

    List<PropertyValue> findByKitOrderByIdDesc(Kit kit);  //根据工具查询
    PropertyValue getByPropertyAndKit(Property property, Kit kit);  //根据工具和属性获取PropertyValue对象
}
