package edu.zsc.cxl.bbs.dao;

import edu.zsc.cxl.bbs.entity.User;

public interface UserDao extends baseDao<User> {

	public void updateByhql(String hql , Object... values);
		
}
