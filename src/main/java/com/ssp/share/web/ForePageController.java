package com.ssp.share.web;

import javax.servlet.http.HttpSession;

import com.ssp.share.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ForePageController {

    /*------------------管理员登录-----------------------------*/
	@GetMapping(value="/")
	public String loginPage() {
		return "redirect:admin";
	}

    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:admin_login";
    }
    
    @GetMapping(value="/admin_login")
    public String login(){
        return "admin/login";
    }
    
    @GetMapping("/index")
    public String index() {
    	return "redirect:admin_index";
    }
    
    @GetMapping("/admin_index")
    public String defaultIndex() {
    	return "admin/index";
    }
    
    @GetMapping(value="/logout")
    public String logout(HttpSession session) {
    	session.removeAttribute("user");
    	return "admin/login";
    }
    /*------------------管理员登录-----------------------------*/

    /*------------------前台注册-----------------------------*/
    @GetMapping(value="/register")
    public String register() {
        return "fore/register";
    }
    @GetMapping(value="/registerSuccess")
    public String registerSuccess() {
        return "fore/registerSuccess";
    }
    /*------------------前台注册-----------------------------*/

    /*------------------前台登录 与 退出-----------------------------*/
    @GetMapping(value="/foreLogin")
    public String foreLogin() {
        return "fore/login";
    }

    @GetMapping(value="/foreLogout")
    public String foreLogout() {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            subject.logout();
        return "redirect:kit";
    }

    /*------------------前台登录-----------------------------*/

    /*------------------工具开始-----------------------------*/
    @GetMapping(value="/kit")
    public String kit() {
        return "fore/kit";
    }

    @GetMapping(value = "/fore_kit_list")
    public String kitList() {
        return "fore/kit";
    }

    @GetMapping(value = "/fore_kit_download")
    public String download() {
        return "fore/kitDownload";
    }
    /*------------------工具结束-----------------------------*/

    /*------------------搜搜开始-----------------------------*/
    @GetMapping(value = "/fore_search")
    public String search() {
        return "fore/searchResult";
    }
    /*------------------搜搜结束-----------------------------*/


    /*------------------个人主页开始-----------------------------*/
    @GetMapping(value = "/personalHomepage")
    public String person() {
        return "fore/personalHomepage";
    }

    @GetMapping(value = "/toTuser")
    public String email() {
        return "fore/emailSucess";
    }

    @GetMapping(value = "/kitUpload")
    public String kitUpload() {
        return "fore/kitUpload";
    }

    @GetMapping(value = "/kitList")
    public String kitPerosonList() {
        return "fore/kitList";
    }

    @GetMapping(value = "/kitCategoryList")
    public String kitCategoryList() {
        return "fore/kitCategoryList";
    }

    @GetMapping(value="/fore_list_kitlist")
    public String listKit(){
        return "fore/kitList";
    }

    @GetMapping(value="/fore_list_kitimage")
    public String kitImage(){
        return "fore/kitImage";
    }

    @GetMapping(value="/fore_list_kitpropertyvalueedit")
    public String kitPropertyValue(){
        return "fore/kitPropertyValueEdit";
    }

    @GetMapping(value="/fore_edit_kit")
    public String editKit(){
        return "fore/kitEdit";
    }
    /*------------------个人主页结束-----------------------------*/

}


