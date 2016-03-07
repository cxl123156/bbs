package edu.zsc.cxl.bbs.action;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import edu.zsc.cxl.bbs.entity.Admin;
import edu.zsc.cxl.bbs.entity.Group;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.AdminService;
import edu.zsc.cxl.bbs.service.CategoryService;
import edu.zsc.cxl.bbs.service.ForumService;
import edu.zsc.cxl.bbs.service.GroupService;
import edu.zsc.cxl.bbs.service.TopicService;
import edu.zsc.cxl.bbs.service.UserService;

public class AdminAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Admin admin;
	private AdminService adminservice;
	private CategoryService categoryService;
	private ForumService forumService;
	private TopicService topicService;
	private UserService userService;
	private String adminId;
	private String password;
	private GroupService groupService;
	
	
	@Autowired
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	@Autowired
	public void setForumService(ForumService forumService) {
		this.forumService = forumService;
	}
	@Autowired
	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	@Autowired
	public void setAdminservice(AdminService adminservice) {
		this.adminservice = adminservice;
	}
	
	public String createAdmin(){
		Date date = new Date();
		User user = new User();
		user.setUserName(admin.getUsername());
		user.setPassword(admin.getPassword());
		user.setEmail(admin.getUsername()+"@bbs.com");
		Group group=groupService.findGroupByName("管理员用户");
		user.setUser_Group(group);
		user.setAvatar("images/defaultAvatar.jpg");
		user.setRegTime(date);
		userService.saveUser(user);
		adminservice.saveAdmin(admin);
		return "createAdmin";
		
	}
	
	public String loginAdmin(){
		System.out.println(admin.getUsername());
	    Admin temp = adminservice.findAdminByName(admin.getUsername());
	    User user = userService.findUserByName(admin.getUsername());
	    getSession().setAttribute("currentUser", user);
	    getSession().setAttribute("currentAdmin", temp);
		return "loginAdmin";
		
	}
	
	public String getInfoAdmin(){
		int categoryNum = categoryService.findAllCategory().size();
		int forumNum = forumService.findAllForum().size();
		int topicNum = topicService.findAllTopic().size();
		int userNum = userService.finAllUser().size();
		int adminNum = adminservice.findAllAdmin().size();
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("categoryNum", categoryNum);
		map.put("forumNum", forumNum);
		map.put("topicNum", topicNum);
		map.put("userNum", userNum);
		map.put("adminNum", adminNum);
		getSession().setAttribute("allInfo", map);
		System.out.println(categoryNum);
		return "getInfoAdmin";
		
	}
	
	public String getAllAdmin(){
		getRequest().setAttribute("allAdmin", adminservice.findAllAdmin());
		return "getAllAdmin";
		
	}
	
	public String deleteAdmin(){
		Long id = Long.parseLong(adminId);
		Admin temp = adminservice.findAdminById(id);
		adminservice.deleteAdmin(temp);
		return "deleteAdmin";	
	}
	
	public String updateAdmin(){
		Long id = Long.parseLong(adminId);
		Admin temp = adminservice.findAdminById(id);
		temp.setUsername(admin.getUsername());
		adminservice.updateAdmin(temp);
		return "updateAdmin";
		
	}
	
	public String editPasswordAdmin(){
		Long id = Long.parseLong(adminId);
		Admin temp = adminservice.findAdminById(id);
		temp.setPassword(password);
		adminservice.updateAdmin(temp);
		return "editPasswordAdmin";
		
	}

}
