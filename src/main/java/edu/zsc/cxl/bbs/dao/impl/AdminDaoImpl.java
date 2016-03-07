package edu.zsc.cxl.bbs.dao.impl;


import org.springframework.stereotype.Repository;

import edu.zsc.cxl.bbs.dao.AdminDao;
import edu.zsc.cxl.bbs.entity.Admin;

@Repository("adminDao")
public class AdminDaoImpl extends baseDaoImpl<Admin> implements AdminDao {

}
