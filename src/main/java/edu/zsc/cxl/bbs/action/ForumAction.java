package edu.zsc.cxl.bbs.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import edu.zsc.cxl.bbs.container.GetForum;
import edu.zsc.cxl.bbs.container.GetForumTopic;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.entity.Admin;
import edu.zsc.cxl.bbs.entity.Category;
import edu.zsc.cxl.bbs.entity.Forum;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.CategoryService;
import edu.zsc.cxl.bbs.service.ForumService;
import edu.zsc.cxl.bbs.service.GetForumService;
import edu.zsc.cxl.bbs.service.GetForumTopicService;
import edu.zsc.cxl.bbs.service.GetTopicService;

public class ForumAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String categoryId;
	private Forum forum;
	private User user;
	private ForumService forumService;
	private CategoryService categoryService;
	private GetForumTopicService getForumTopicService;
	private int page=1;
	private GetForumService getForumService;
	private Category category;
	private String forumAdmin;


	public String getForumAdmin() {
		return forumAdmin;
	}


	public void setForumAdmin(String forumAdmin) {
		this.forumAdmin = forumAdmin;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	@Autowired
	public void setGetForumService(GetForumService getForumService) {
		this.getForumService = getForumService;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	@Autowired
	public void setGetForumTopicService(GetForumTopicService getForumTopicService) {
		this.getForumTopicService = getForumTopicService;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Forum getForum() {
		return forum;
	}
	
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public CategoryService getCategoryService() {
		return categoryService;
	}

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	

	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public ForumService getForumService() {
		return forumService;
	}
	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	
	public String adminCreateForum(){
		Admin admin = (Admin) getSession().getAttribute("currentAdmin");
		Long id =Long.parseLong(categoryId);
		Category category = categoryService.findCategoryById(id);
		System.out.println(category.getTittle());
		forum.setCategory_Forum(category);
		forum.setCreateForumAdmin(admin);
		forumService.saveForum(forum);
		return "adminCreateForum";		
	}
	
	public String deleteForum(){
		Forum temp = forumService.findForumById(forum.getForumId());
		forumService.deleteForum(temp);
		return "deleteForum";
	}
	
	public String findForum(){
		forum=forumService.findForumById(forum.getForumId());
		Map<Category,Forum> forumMap = new HashMap<Category,Forum>();
		forumMap.put(forum.getCategory_Forum(),forum);
		getSession().setAttribute("currentForum", forumMap);
		return "findForum";
	}
	
	public String showTopicForum(){

		if(forumAdmin!=null&&forumAdmin.equals("1")){
			PageModel<GetForumTopic> pm = new PageModel<GetForumTopic>();	
			pm.setPageSize(5);
			pm.setPageNo(page);
			List<GetForumTopic> list =getForumTopicService.getAllTopicForum(forum.getForumId(), pm);
			getRequest().setAttribute("adminCurrentForumTopic", list);
			return "adminShowTopicForum";
		}
		else{
			forum=forumService.findForumById(forum.getForumId());
			Map<Category,Forum> forumMap = new HashMap<Category,Forum>();
			forumMap.put(forum.getCategory_Forum(),forum);
			getSession().setAttribute("currentForum", forumMap);
			PageModel<GetForumTopic> pm = new PageModel<GetForumTopic>();	
			pm.setPageSize(5);
			List<GetForumTopic> list =getForumTopicService.getAllTopicForum(forum.getForumId(), pm);
			getRequest().setAttribute("currentForumTopic", list);
			return "showTopicForum";

		}
		
	}
	
	/**
	 * @return
	 */
	public String updateForum(){
		Forum temp = forumService.findForumById(forum.getForumId());
		temp.setCategory_Forum(categoryService.findCategoryById(category.getCategoryId()));
		temp.setTittle(forum.getTittle());
		temp.setDescription(forum.getDescription());
		temp.setForumRul(forum.getForumRul());
		forumService.updateForum(temp);
		return "updateForum";
		
	}
	
	public String getCategoryAndForum(){
		HashMap<List<Category>, LinkedHashMap<Category, List<Forum>>> hm =categoryService.getForumAndCategory();
		for(List<Category> list : hm.keySet()){
			getRequest().setAttribute("category", list);
			getRequest().setAttribute("cateAndForum", hm.get(list));
		}
		return "getCategoryAndForum";
	}
	
	public void ajaxGetForum() throws IOException{
		System.out.println(categoryId);
		Long id = Long.parseLong(categoryId);
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = getResponse().getWriter();
		
		List<Forum> forumList = forumService.findForumBycategory(id);
		Map<Long, String> forumMap =new LinkedHashMap<Long, String>();
		for(Forum f : forumList){
			forumMap.put(f.getForumId(), f.getTittle());
		}
		Gson gson=new Gson();
		String json = gson.toJson(forumMap);
		System.out.println(json);
		out.print(json);
	}
	
}
