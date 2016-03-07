package edu.zsc.cxl.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.CategoryAndForumDao;
import edu.zsc.cxl.bbs.dao.baseDao;
import edu.zsc.cxl.bbs.entity.CategoryAndForum;
import edu.zsc.cxl.bbs.service.CAndFService;

@Service("cAndFService")
public class CAndFServiceImpl implements CAndFService {

	CategoryAndForumDao cAndFDao;

	

	public CategoryAndForumDao getcAndFDao() {
		return cAndFDao;
	}

	@Autowired
	public void setcAndFDao(CategoryAndForumDao cAndFDao) {
		this.cAndFDao = cAndFDao;
	}



	@Override
	public List<Object[]> findCategoryAndForum() {
		String hql="select BBS_CATEGORY.tittle as ctittle, BBS_FORUM.tittle as ftittle from  BBS_CATEGORY ,  BBS_FORUM where BBS_CATEGORY.categoryId=BBS_FORUM.categoryId";	
	
		return cAndFDao.getListBySQL(hql);
	}

}
