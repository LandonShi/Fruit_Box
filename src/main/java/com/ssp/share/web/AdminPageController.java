package com.ssp.share.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

	//------------分类---------------------------
	@GetMapping(value = "managerKitCategoryList")
	public String kitCategory() {
		return "redirect:admin_list_kitcategory";
	}

	@GetMapping(value = "admin_list_kitcategory")
	public String defaultKitCategory() {
		return "admin/adminKitCategoryList";
	}

	@GetMapping(value = "admin_edit_kitcategory")
	public String kitCategoryEdit() {
		return "admin/adminKitCategoryEdit";
	}
	//------------分类---------------------------

	//------------后台工具管理---------------------------
	@GetMapping(value = "admin_list_kitlistun")   //未审核
	public String aminKitListUn() {
		return "admin/adminKitListUn";
	}
	@GetMapping(value = "admin_list_kitlist")    //已审核
	public String aminKitList() {
		return "admin/adminKitList";
	}

	@GetMapping(value = "admin_kit_detail")
	public String adminKitDetail() {
		return "admin/adminKitDetail";
	}
	//------------后台工具管理---------------------------


	//------------分类属性---------------------------
	@GetMapping(value="/admin_list_kitcategoryproperty")
	public String listProperty(){ return "admin/kitCategoryPropertyList"; }
	@GetMapping(value="/admin_edit_kitcategoryproperty")
	public String listKitCategoryProperty(){ return "admin/kitCategoryPropertyEdit"; }
	//------------分类属性---------------------------

	//------------用户管理---------------------------
	@GetMapping("/manager")
	public String manager() {
		return "redirect:admin_manager";
	}

	@GetMapping("/admin_manager")
	public String returnManager() {
		return "admin/manager";
	}

	@GetMapping("/user_search")
	public String searchUser() {
		return "admin/userSearchResult";
	}

	@GetMapping("/manager_search")
	public String searchManager() {
		return "admin/managerSearchResult";
	}
}
