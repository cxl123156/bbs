package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.LoginLogDao;
import edu.zsc.cxl.bbs.entity.LoginLog;
import edu.zsc.cxl.bbs.service.LoginLogService;

@Service("loginLogService")
public class LoginLogServiceImpl implements LoginLogService {

	private LoginLogDao loginLogDao;
	
	public LoginLogDao getLoginLogDao() {
		return loginLogDao;
	}
    @Autowired
	public void setLoginLogDao(LoginLogDao loginLogDao) {
		this.loginLogDao = loginLogDao;
	}

	@Override
	public void saveLog(LoginLog loginLog) {
		loginLogDao.save(loginLog);
	}

	@Override
	public void updateLog(LoginLog loginLog) {
		loginLogDao.update(loginLog);
	}

	@Override
	public List<LoginLog> findLogByUser(Serializable userId) {
		String hql = "from LoginLog where userId = ?";		
		return loginLogDao.getListByHQL(hql, userId);
	}

	@Override
	public void deleteLog(LoginLog loginLog) {
		loginLogDao.delete(loginLog);
	}

}
