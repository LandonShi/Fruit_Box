package com.ssp.share.web;

import com.ssp.share.pojo.Kit;
import com.ssp.share.pojo.KitImage;
import com.ssp.share.service.CategoryService;
import com.ssp.share.service.KitImageService;
import com.ssp.share.service.KitService;
import com.ssp.share.util.ImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class KitImageController {

    @Autowired
    KitService kitService;
    @Autowired
    KitImageService kitImageService;
    @Autowired
    CategoryService categoryService;

    @GetMapping("/kits/{kid}/kitImages")
    public List<KitImage> list(@RequestParam("type") String type, @PathVariable("kid") int kid) {
        Kit kit = kitService.get(kid);
        if(KitImageService.type_single.equals(type)) {
            List<KitImage> singles =  kitImageService.listSingleKitImages(kit);
            return singles;
        }
        else if(KitImageService.type_detail.equals(type)) {
            List<KitImage> details =  kitImageService.listDetailKitImages(kit);
            return details;
        }
        else {
            return new ArrayList<>();
        }
    }

    @PostMapping("/kitImages")
    public Object add(@RequestParam("kid") int kid, @RequestParam("type") String type, MultipartFile image, HttpServletRequest request) {
        KitImage bean = new KitImage();
        Kit kit = kitService.get(kid);
        bean.setKit(kit);
        bean.setType(type);

        kitImageService.add(bean);
        String folder = "img/";
        if(KitImageService.type_single.equals(bean.getType())){
            folder +="kitSingle";
        }
        else{
            folder +="kitDetail";
        }
        File  imageFolder= new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder,bean.getId()+".jpg");
        String fileName = file.getName();
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(KitImageService.type_single.equals(bean.getType())){
            String imageFolder_small= request.getServletContext().getRealPath("img/kitSingle_small");
            String imageFolder_middle= request.getServletContext().getRealPath("img/kitSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }
        return bean;
    }

    @DeleteMapping("/kitImages/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request) {
        KitImage bean = kitImageService.get(id);
        kitImageService.delete(id);
        String folder = "img/";
        if(KitImageService.type_single.equals(bean.getType()))
            folder +="kitSingle";
        else
            folder +="kitDetail";
        File imageFolder= new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder,bean.getId()+".jpg");
        String fileName = file.getName();
        file.delete();
        if(KitImageService.type_single.equals(bean.getType())){
            String imageFolder_small= request.getServletContext().getRealPath("img/kitSingle_small");
            String imageFolder_middle= request.getServletContext().getRealPath("img/kitSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.delete();
            f_middle.delete();
        }
        return null;
    }
}
