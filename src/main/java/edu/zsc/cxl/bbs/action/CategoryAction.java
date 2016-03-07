package edu.zsc.cxl.bbs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.json.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.jasperreports.JasperReportsUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.zsc.cxl.bbs.container.GetCategory;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.entity.Admin;
import edu.zsc.cxl.bbs.entity.Category;
import edu.zsc.cxl.bbs.entity.Forum;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.CAndFService;
import edu.zsc.cxl.bbs.service.CategoryService;
import edu.zsc.cxl.bbs.service.GetCategoryService;

public class CategoryAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private Category category;
	private CategoryService categoryService;
	private CAndFService cAndFService;
	private GetCategoryService getCategoryService;
	private int page  = 1;
	private String admin;
	
	
	
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	@Autowired
	public void setGetCategoryService(GetCategoryService getCategoryService) {
		this.getCategoryService = getCategoryService;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	public CAndFService getcAndFService() {
		return cAndFService;
	}
	public void setcAndFService(CAndFService cAndFService) {
		this.cAndFService = cAndFService;
	}
	public String createCategory(){
		Admin admin = (Admin) getSession().getAttribute("currentAdmin");
		category.setCreateCategoryUser(admin);
		categoryService.saveCategory(category);
		return "createCategory";
	}
	
	public String editCategory(){
		
//		User user = (User) getSession().getAttribute("currentUser");
		Category temp = categoryService.findCategoryById(category.getCategoryId());
		category.setCreateCategoryUser(temp.getCreateCategoryUser());
		categoryService.updateCategory(category);
		return "editCategory";
		
	}
	
	public String deleteCategory(){
		Category temp = categoryService.findCategoryById(category.getCategoryId());
		categoryService.deleteCategory(temp);
		return "deleteCategory";	
	}

	public String findAllCategory(){
		List<Category> categoryList = categoryService.findAllCategory();
		getApplication().setAttribute("category", categoryList);
		return "findAllCategory";	
	}
	
	public String adminFindAllCategory(){
		PageModel<GetCategory> pm = new PageModel<GetCategory>();
		pm.setPageNo(page);
		pm.setPageSize(10);
		List<GetCategory> categoryList = getCategoryService.getAllCategory(pm);
		getRequest().setAttribute("getCategory", categoryList);
		return "adminFindAllCategory";	
	}
	
	public String getForumAndCategory(){
		HashMap<List<Category>, LinkedHashMap<Category, List<Forum>>> hm =categoryService.getForumAndCategory();
		for(List<Category> list : hm.keySet()){
			getApplication().setAttribute("category", list);
			getApplication().setAttribute("cateAndForum", hm.get(list));
		}
		if(admin.equals("1")){
			return "adminGetForumAndCategory";
		}
		else{
			return "getForumAndCategory";
		}

	}
	
	public String adminGetForumAndCategory(){
		HashMap<List<Category>, LinkedHashMap<Category, List<Forum>>> hm =categoryService.getForumAndCategory();
		for(List<Category> list : hm.keySet()){
			getRequest().setAttribute("category", list);
			getRequest().setAttribute("cateAndForum", hm.get(list));
		}
		return "adminGetForumAndCategory";		
	}
	
	
}
