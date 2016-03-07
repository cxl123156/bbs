package edu.zsc.cxl.bbs.action;


import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;

import edu.zsc.cxl.bbs.entity.Admin;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.AdminService;
import edu.zsc.cxl.bbs.service.UserService;

public class ValidateAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String validateCode;
	private AdminService adminService;
	private UserService userService;



	@Autowired
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public void nameAvailableValidate() throws IOException{		
		PrintWriter out = getResponse().getWriter();
		if(userService.findUserByName(username)==null){
			out.println(true);
		}
		else {
			out.println(false);
		}	
	}
	
	public void adminLoginValidate() throws IOException{
		PrintWriter out = getResponse().getWriter();
		try {
			Admin admin = adminService.findAdminByName(username);

			System.out.println(username);
			if(admin.getPassword().equals(password)){
				out.println(true);
			}
			else {
				out.println(false);
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			out.println(false);
//			e.printStackTrace();
		}
	}
	
	public void loginValidate() throws IOException{
		PrintWriter out = getResponse().getWriter();
		try {
			User user = userService.findUserByName(username);

			System.out.println(username);
			if(user.getPassword().equals(password)){
				out.println(true);
			}
			else {
				out.println(false);
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			out.println(false);
//			e.printStackTrace();
		}
	}
	
	public void codeValidate() throws IOException{
		PrintWriter out = getResponse().getWriter();
		String code = (String) getSession().getAttribute("valiCode");
		if(validateCode.toLowerCase().equals(code.toLowerCase())){ //.toLowerCase()大写字母转小写
			out.print(true);
		}
		else {
			out.print(false);
		}
	}
	
	public void passwordValidate() throws IOException{
		PrintWriter out = getResponse().getWriter();
		User user =(User) getSession().getAttribute("currentUser");
		System.out.println(password);
		if(password.equals(user.getPassword())){
			out.print(true);
		}
		else {
			out.print(false);
		}
	}
	
	public void checkLoginValidate() throws IOException{
		PrintWriter out = getResponse().getWriter();
		if(getSession().getAttribute("currentUser").equals("") || getSession().getAttribute("currentUser")==null)
			out.print(false);
		else {
			out.print(true);
		}
	}
	
	public void adminNameAvailableValidate() throws IOException{		
		PrintWriter out = getResponse().getWriter();
		if(adminService.findAdminByName(username)==null){
			out.println(true);
		}
		else {
			out.println(false);
		}	
	}
	
}

