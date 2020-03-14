package com.ssp.share.web;

import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.PropertyValue;
import com.ssp.share.service.KitService;
import com.ssp.share.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyValueController {

    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    KitService kitService;

    @GetMapping("/kits/{kid}/propertyValues")
    public List<PropertyValue> list(@PathVariable("kid") int kid) {
        Kit kit = kitService.get(kid);
        propertyValueService.init(kit);
        List<PropertyValue> propertyValues = propertyValueService.list(kit);
        return propertyValues;
    }

    @PutMapping("/propertyValues")
    public Object update(@RequestBody PropertyValue bean) {
        propertyValueService.update(bean);
        return bean;
    }

    @GetMapping("foreKitPV/{kid}/propertyValues")
    public List<PropertyValue> listPV(@PathVariable("kid") int kid) {
        Kit kit = kitService.get(kid);
        propertyValueService.init(kit);
        List<PropertyValue> propertyValues = propertyValueService.list(kit);
        return propertyValues;
    }

}
