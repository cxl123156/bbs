package edu.zsc.cxl.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.container.GetCategory;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.dao.GetCategoryDao;
import edu.zsc.cxl.bbs.service.GetCategoryService;

@Service("getCategoryService")
public class GetCategoryServiceImpl implements GetCategoryService {

	GetCategoryDao getCategoryDao;
		
	@Autowired
	public void setGetCategoryDao(GetCategoryDao getCategoryDao) {
		this.getCategoryDao = getCategoryDao;
	}

	@Override
	public List<GetCategory> getAllCategory(PageModel<GetCategory> pm) {
		String hql = "select new edu.zsc.cxl.bbs.container.GetCategory(c.categoryId,c.tittle,c.createTime,a.username,c.description)"
				+ " from Category c , Admin a where c.createCategoryUser = a.adminId";
		return getCategoryDao.findInPageByHQL(hql, pm);
	}

}
