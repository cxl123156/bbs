package edu.zsc.cxl.bbs.dao;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import edu.zsc.cxl.bbs.container.PageModel;


public interface baseDao<T> {
	public Serializable save(T entity);

	public void delete(T entity);

	public void update(T entity);

	public T findById(Serializable id);

	public T getByHQL(String hqlString, Object... values);

	public T getBySQL(String sqlString, Object... values);
	
	public int getIntBySQL(String sqlString);

	public List<T> getListByHQL(String hqlString, Object... values);

	public List<T> getListBySQL(String sqlString, Object... values);

	public T insertBySQL(String sqlString, Object... values);
	
	public List<T> findInPageByHQL(String hql , PageModel<T> pageModel);
	
	public void deleteByHQL(String hql);

}
