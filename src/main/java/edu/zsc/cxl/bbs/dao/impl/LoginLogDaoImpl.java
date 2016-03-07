package edu.zsc.cxl.bbs.dao.impl;

import org.springframework.stereotype.Repository;

import edu.zsc.cxl.bbs.dao.LoginLogDao;
import edu.zsc.cxl.bbs.entity.LoginLog;

@Repository("loginLogDao")
public class LoginLogDaoImpl extends baseDaoImpl<LoginLog>  implements LoginLogDao{

}
