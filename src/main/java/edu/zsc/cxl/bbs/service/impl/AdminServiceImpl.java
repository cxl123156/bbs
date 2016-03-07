package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.AdminDao;
import edu.zsc.cxl.bbs.entity.Admin;
import edu.zsc.cxl.bbs.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	AdminDao adminDao;
	
	
	
	public AdminDao getAdminDao() {
		return adminDao;
	}

	@Autowired
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}

	@Override
	public void saveAdmin(Admin admin) {
		adminDao.save(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		adminDao.update(admin);
	}

	@Override
	public void deleteAdmin(Admin admin) {
		adminDao.delete(admin);
	}

	@Override
	public Admin findAdminByName(String adminName) {
		String hql = "from Admin where username = ?";
		return adminDao.getByHQL(hql,adminName);
	}

	@Override
	public Admin findAdminById(Serializable id) {
		return adminDao.findById(id);
	}

	@Override
	public List<Admin> findAllAdmin() {
		String hql = "from Admin";
		return adminDao.getListByHQL(hql);
	}

}
