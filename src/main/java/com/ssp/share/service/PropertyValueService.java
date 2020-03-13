package com.ssp.share.service;


import com.ssp.share.dao.PropertyValueDAO;
import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.Property;
import com.ssp.share.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames="propertyValues")
public class PropertyValueService {

    @Autowired
    PropertyValueDAO propertyValueDAO;
    @Autowired
    PropertyService propertyService;

    @CacheEvict(allEntries=true)
    public void update(PropertyValue bean) {
        propertyValueDAO.save(bean);
    }

    @CacheEvict(allEntries=true)
    public void delete(int pid) {
        propertyValueDAO.delete(pid);
    }

    public void init(Kit kit) {
        List<Property> propertys = propertyService.listByCategory(kit.getCategory());
        for (Property property: propertys) {
            PropertyValue propertyValue = getByPropertyAndKit(kit, property);
            if(null == propertyValue){
                propertyValue = new PropertyValue();
                propertyValue.setKit(kit);
                propertyValue.setProperty(property);
                propertyValueDAO.save(propertyValue);
            }
        }
    }

    @Cacheable(key="'propertyValues-one-kid-'+#p0.id+ '-pid-' + #p1.id")
    public PropertyValue getByPropertyAndKit(Kit kit, Property property) {
        return propertyValueDAO.getByPropertyAndKit(property,kit);
    }

    @Cacheable(key="'propertyValues-pid-'+ #p0.id")
    public List<PropertyValue> list(Kit kit) {
        return propertyValueDAO.findByKitOrderByIdDesc(kit);
    }
}
