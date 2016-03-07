package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.entity.LoginLog;

public interface LoginLogService {
	public void saveLog(LoginLog loginLog);
	public void updateLog(LoginLog loginLog);
	public List<LoginLog> findLogByUser(Serializable userId);
	public void deleteLog(LoginLog loginLog);
}
