package edu.zsc.cxl.bbs.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opensymphony.xwork2.ActionContext;

import edu.zsc.cxl.bbs.dao.UserDao;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public User findUserById(Serializable id) {
		return userDao.findById(id);
	}

	@Override
	public User findUserByName(String username) {
		String hql = "from User where username=?";
		return userDao.getByHQL(hql, username);
	}

	@Override
	public void updatePassword(String password , Serializable id){
		String hql = "update User set password = "+password+"where user.id = ?";
		userDao.getByHQL(hql,id);
	}

	@Override
	public String uploadAvatar(String imageFileName, String folder,File image) throws IOException {
		   String realpath = ServletActionContext.getServletContext().getRealPath("/userUpload/"+folder);
		   if (image != null) {
	            File savefile = new File(new File(realpath), imageFileName);
	            if (!savefile.getParentFile().exists())
	                savefile.getParentFile().mkdirs();
	            FileUtils.copyFile(image, savefile);
	            ActionContext.getContext().put("message", "文件上传成功");
	        }
		return folder+"/"+imageFileName;
	}

	@Override
	public List<User> finAllUser() {
		String hql = " from User";
		return userDao.getListByHQL(hql);
	}
	
}
