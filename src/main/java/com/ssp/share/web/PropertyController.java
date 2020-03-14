package com.ssp.share.web;

import com.ssp.share.pojo.Property;
import com.ssp.share.service.CategoryService;
import com.ssp.share.service.PropertyService;
import com.ssp.share.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class PropertyController {
    @Autowired
    PropertyService propertyService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/kitcategories/{cid}/properties")
    public Page4Navigator<Property> list(@PathVariable("cid") int cid, @RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) {
        start = start<0?0:start;
        Page4Navigator<Property> page =propertyService.list(cid, start, size,5);
        return page;
    }

    @GetMapping("/properties/{id}")
    public Property get(@PathVariable("id") int id) {
        Property bean=propertyService.get(id);
        return bean;
    }

    @PostMapping("/properties")
    public Object add(@RequestBody Property bean) {
        propertyService.add(bean);
        return bean;
    }

    @DeleteMapping("/properties/{id}")
    public String delete(@PathVariable("id") int id) {
        propertyService.delete(id);
        return null;
    }

    @PutMapping("/properties")
    public Object update(@RequestBody Property bean) {
        propertyService.update(bean);
        return bean;
    }

}
