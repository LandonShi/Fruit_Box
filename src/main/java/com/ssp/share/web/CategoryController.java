package com.ssp.share.web;


import com.ssp.share.pojo.Category;
import com.ssp.share.service.CategoryService;
import com.ssp.share.util.ImageUtil;
import com.ssp.share.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/kitcategories")
    public Page4Navigator<Category> list(@RequestParam(value = "start", defaultValue = "0") int start,
                                         @RequestParam(value = "size", defaultValue = "13") int size) {
        start = start<0?0:start;
        Page4Navigator page =categoryService.list(start, size, 5);  //5表示导航分页最多有5个，像 [1,2,3,4,5] 这样
        return page;
    }

    @PostMapping("/kitcategories")
    public Object add(MultipartFile image, HttpServletRequest request) throws Exception {
        Category bean = new Category();
        bean.setName(request.getParameter("name"));
        categoryService.add(bean);
        saveOrUpdateImageFile(bean, image, request);
        return bean;
    }
    public void saveOrUpdateImageFile(Category bean, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/kitcategory"));
        File file = new File(imageFolder,bean.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }


    @GetMapping("/kitcategories/{cid}")
    public Category get(@PathVariable("cid") int cid) {
        Category bean=categoryService.get(cid);
        return bean;
    }

    @DeleteMapping("/kitcategories/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) {
        categoryService.delete(id);
        File  imageFolder= new File(request.getServletContext().getRealPath("img/kitcategory"));
        File file = new File(imageFolder,id+".jpg");
        file.delete();
        return null;
    }

    @PutMapping("/kitcategories/{id}")
    public Object update(Category bean, MultipartFile image,HttpServletRequest request) throws IOException {
        String name = request.getParameter("name");
        bean.setName(name);
        categoryService.update(bean);
        if(image!=null) {
            saveOrUpdateImageFile(bean, image, request);
        }
        return bean;
    }


}
