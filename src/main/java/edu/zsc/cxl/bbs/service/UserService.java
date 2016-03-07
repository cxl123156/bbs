package edu.zsc.cxl.bbs.service;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.entity.User;

public interface UserService {

	public void saveUser(User user);
	public void updateUser(User user);
	public void deleteUser(User user);
	public User findUserById(Serializable id);
	public User findUserByName(String username); 
	public void updatePassword(String password , Serializable id);
	public String uploadAvatar(String imageFileName , String folder, File image) throws IOException;
	public List<User> finAllUser();
}
