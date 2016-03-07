package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.entity.Admin;

public interface AdminService {

	public void saveAdmin(Admin admin);
	public void updateAdmin(Admin admin);
	public void deleteAdmin(Admin admin);
	public Admin findAdminByName(String adminName);
	public Admin findAdminById(Serializable id);
	public List<Admin> findAllAdmin();
}
