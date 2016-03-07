package edu.zsc.cxl.bbs.dao.impl;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import edu.zsc.cxl.bbs.dao.UserDao;
import edu.zsc.cxl.bbs.entity.User;

@Repository("userDao")
public class UserDaoImpl extends baseDaoImpl<User> implements UserDao {

	@Override
	public void updateByhql(String hql , Object... values) {
		// TODO Auto-generated method stub
		Query query =  this.getCurrentSession().createQuery(hql);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
	}


}
