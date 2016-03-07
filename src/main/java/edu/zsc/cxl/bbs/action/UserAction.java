package edu.zsc.cxl.bbs.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import edu.zsc.cxl.bbs.entity.LoginLog;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.GroupService;
import edu.zsc.cxl.bbs.service.LoginLogService;
import edu.zsc.cxl.bbs.service.UserService;

public class UserAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;
	private UserService userService;
	private LoginLogService loginLogService;
	private File image;
	private String imageFileName;
	private String imageContentType;
	private String userName;
	private GroupService groupService;
	private String userId;
	private String admin;
	private String groupId;
	
	
	
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Autowired
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Autowired
	public void setLoginLogService(LoginLogService loginLogService) {
		this.loginLogService = loginLogService;
	}
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String regUser(){
		String ip = getRequest().getRemoteAddr();
		Date currentTime = new Date(System.currentTimeMillis());
		user.setLoginIp(ip);
		user.setRegTime(currentTime);
		user.setLoginTime(currentTime);
		user.setUser_Group(groupService.findGroupByName("普通用户"));
		user.setAvatar("images/defaultAvatar.jpg");
		userService.saveUser(user);
		if(admin!=null&&admin.equals("1")){
			return "adminRegUser";
		}
		else{
		getSession().setAttribute("currentUser", user);
		return "regUser";
		}
	}
	
	public String loginUser(){
		user=userService.findUserByName(user.getUserName());
		String ip = getRequest().getRemoteAddr();
		Date loginTime = new Date(System.currentTimeMillis());
		user.setLoginIp(ip);
		user.setLoginTime(loginTime);
		getSession().setAttribute("currentUser", user);
		return "loginUser";
	}
	
	public String logoutUser(){
		LoginLog loginLog = new LoginLog();
		Set<LoginLog> logSet = new HashSet<LoginLog>();
		Date logoutTime = new Date(System.currentTimeMillis());
		user=(User) getSession().getAttribute("currentUser");
		loginLog.setLoginTime(user.getLoginTime());
		loginLog.setLogoutTime(logoutTime);
		loginLog.setIp(getRequest().getRemoteAddr());
		loginLog.setUserLog(user);
		loginLogService.saveLog(loginLog);
		getSession().removeAttribute("currentUser");
		getSession().invalidate();
		return "logoutUser";		
	}

	
	public String editUser() throws IOException{
		
		User temp = (User) getSession().getAttribute("currentUser");
		user.setUserName(temp.getUserName());
		user.setUserId(temp.getUserId());
		user.setAvatar(temp.getAvatar());
		user.setPassword(temp.getPassword());
		user.setRegTime(temp.getRegTime());
		user.setLoginIp(temp.getLoginIp());
		user.setLoginTime(temp.getLoginTime());
		if(user.getEmail()==null || user.getEmail().equals("")){
		     user.setEmail(temp.getEmail());
		}
		userService.updateUser(user);
		getSession().removeAttribute("currentUser");
		getSession().setAttribute("currentUser", user);
		getSession().removeAttribute("image");
		return "editUser";
		
	}
	
	public String editPasswordUser(){
		User temp = (User) getSession().getAttribute("currentUser");	
		temp.setPassword(user.getPassword());
		userService.updateUser(temp);
		return "editPasswordUser";

	}
	
	public String findByNameUser(){	
		User u = userService.findUserByName(userName);
		getRequest().setAttribute("findUserResult", u);
		return "findByNameUser";		
	}
	
	public String showUser(){
		
		User temp = (User) getSession().getAttribute("currentUser");
		userService.findUserById(temp.getUserId());
		return "showUser";
	}
	
	public String uploadUser() throws IOException{
		User temp = (User) getSession().getAttribute("currentUser");
		System.out.println(imageFileName+" "+image);
        String url;
		url = userService.uploadAvatar(imageFileName,temp.getUserName(), image);
		temp.setAvatar(url);
		userService.updateUser(temp);
        System.out.println(url);
        getSession().setAttribute("image",url);
        return "uploadUser";
    }
	
	public String getAllUser(){
		getRequest().setAttribute("group", groupService.findAllGroup());
		getRequest().setAttribute("allUsers",userService.finAllUser());
		return "getAllUser";
	}
	
	public String deleteUser(){
		Long id = Long.parseLong(userId);
		User temp = userService.findUserById(id);
		userService.deleteUser(temp);
		return "deleteUser";
		
	}
	public String editGroupUser(){
		Long id = Long.parseLong(userId);
		Long gId=Long.parseLong(groupId);
		User temp = userService.findUserById(id);
		temp.setUser_Group(groupService.findGroupById(gId));
		userService.updateUser(temp);
		return "editGroupUser";
		
	}
	
}
