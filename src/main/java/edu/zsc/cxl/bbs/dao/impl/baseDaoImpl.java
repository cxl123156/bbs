package edu.zsc.cxl.bbs.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.sql.Update;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.Connection;

import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.dao.baseDao;


@Repository("baseDao")
public class baseDaoImpl<T> implements baseDao<T> {
	private SessionFactory sessionFactory;
	protected Class<T> entityClass;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	protected Class getEntityClass() {
		if (entityClass == null) {
			entityClass = (Class<T>) ((ParameterizedType) getClass()
					.getGenericSuperclass()).getActualTypeArguments()[0];
		}
		return entityClass;
	}

	@Transactional
	@Override
	public Serializable save(T entity) {
		return this.getCurrentSession().save(entity);
	}

	@Transactional
	@Override
	public void delete(T entity) {
		this.getCurrentSession().delete(entity);
		
	}

	@Transactional
	@Override
	public void update(T entity) {
		this.getCurrentSession().update(entity);

	}
	@Transactional
	@Override
	public T findById(Serializable id) {
		 T load = (T) this.getCurrentSession().get(getEntityClass(), id);
	     return load;
	}
    /**
     * <根据HQL语句查找唯一实体>
     * @param hqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询实体
     * @see com.itv.launcher.util.IBaseDao#getByHQL(java.lang.String, java.lang.Object[])
     */
	@Transactional
    @Override
    public T getByHQL(String hqlString, Object... values) {
        Query query = this.getCurrentSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }
    /**
     * <根据SQL语句查找唯一实体>
     * @param sqlString SQL语句
     * @param values 不定参数的Object数组
     * @return 查询实体
     * @see com.itv.launcher.util.IBaseDao#getBySQL(java.lang.String, java.lang.Object[])
     */
	@Transactional
    @Override
    public T getBySQL(String sqlString, Object... values) {
        Query query = this.getCurrentSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return (T) query.uniqueResult();
    }
    /**
     * <根据HQL语句，得到对应的list>
     * @param hqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询多个实体的List集合
     * @see com.itv.launcher.util.IBaseDao#getListByHQL(java.lang.String, java.lang.Object[])
     */
	@Transactional
    @Override
    public List<T> getListByHQL(String hqlString, Object... values) {
        Query query = this.getCurrentSession().createQuery(hqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }
 
    /**
     * <根据SQL语句，得到对应的list>
     * @param sqlString HQL语句
     * @param values 不定参数的Object数组
     * @return 查询多个实体的List集合
     * @see com.itv.launcher.util.IBaseDao#getListBySQL(java.lang.String, java.lang.Object[])
     */
	@Transactional
    @Override
    public List<T> getListBySQL(String sqlString, Object... values ) {
        Query query = this.getCurrentSession().createSQLQuery(sqlString);
        if (values != null)
        {
            for (int i = 0; i < values.length; i++)
            {
                query.setParameter(i, values[i]);
            }
        }
        return query.list();
    }
	
	@Transactional
    @Override
	public T insertBySQL(String sqlString, Object... values){
		int update = this.getCurrentSession().createSQLQuery(sqlString).executeUpdate();
		return null;	
		
	}

	@Transactional
	@Override
	public List<T> findInPageByHQL(String hql , PageModel<T> pageModel) {
		Query query = this.getCurrentSession().createQuery(hql);
		query.setFirstResult((pageModel.getPageNo()-1)*pageModel.getPageSize());
		query.setMaxResults(pageModel.getPageSize());
		List<T> list = query.list();
		return list;
	}

	@Transactional
	@Override
	public void deleteByHQL(String hql) {
		// TODO Auto-generated method stub
		Query query = this.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}

	@Transactional
	@Override
	public int getIntBySQL(String sqlString) {
		Query query = this.getCurrentSession().createQuery(sqlString);
		return (int) query.uniqueResult();
	}
	
	

}
