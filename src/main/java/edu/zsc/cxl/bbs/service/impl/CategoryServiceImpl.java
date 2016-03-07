package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.CategoryDao;
import edu.zsc.cxl.bbs.dao.baseDao;
import edu.zsc.cxl.bbs.entity.Category;
import edu.zsc.cxl.bbs.entity.Forum;
import edu.zsc.cxl.bbs.service.CategoryService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	CategoryDao categoryDao;
	
	
	public CategoryDao getCategoryDao() {
		return categoryDao;
	}

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void saveCategory(Category category) {
		categoryDao.save(category);		
	}

	@Override
	public Category findCategoryByTittle(String tittle) {
		String hql = "from Category where tittle = ?";	
		return categoryDao.getByHQL(hql, tittle);
	}

	
	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		categoryDao.update(category);
	}

	@Override
	public void deleteCategory(Category category) {
		categoryDao.delete(category);
	}

	@Override
	public Category findCategoryById(Serializable id) {
		return categoryDao.findById(id);
	}

	@Override
	public List<Category> findAllCategory() {
		String hql = "from Category";	
		return categoryDao.getListByHQL(hql);
	}

	@Override
	public int getCategoryNum() {
		String sql = "select count(*) from BBS_CATEGORY";
		int num =categoryDao.getIntBySQL(sql);
		System.out.println(categoryDao.getBySQL(sql));
		return num;
	}

	@Override
	public HashMap<List<Category>, LinkedHashMap<Category, List<Forum>>> getForumAndCategory() {
		List<Category> catList=this.findAllCategory();
		Iterator<Category> itCate = catList.iterator();
		LinkedHashMap<Category, List<Forum>> catMap = new LinkedHashMap<Category, List<Forum>>();
		Category cat;
		while(itCate.hasNext()){
			List<Forum> forumList = new ArrayList<>();
			cat=itCate.next();
			List<Forum> getForumList =new ArrayList<Forum>();
			getForumList=cat.getForumId();
			Iterator<Forum> itForum = getForumList.iterator();
			while(itForum.hasNext()){
				forumList.add(itForum.next());
			}
			catMap.put(cat, forumList);
		}
		HashMap<List<Category>, LinkedHashMap<Category, List<Forum>>> hm = new HashMap<List<Category>, LinkedHashMap<Category, List<Forum>>>();
		hm.put(catList, catMap);
		return hm;
	}

	
	

}
