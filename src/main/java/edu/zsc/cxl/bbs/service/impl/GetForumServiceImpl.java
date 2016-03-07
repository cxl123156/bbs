package edu.zsc.cxl.bbs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.container.GetForum;
import edu.zsc.cxl.bbs.dao.GetForumDao;
import edu.zsc.cxl.bbs.service.GetForumService;

@Service("getForumService")
public class GetForumServiceImpl implements GetForumService {

	GetForumDao getForumDao;
	
	
	
	public GetForumDao getGetForumDao() {
		return getForumDao;
	}



	@Autowired
	public void setGetForumDao(GetForumDao getForumDao) {
		this.getForumDao = getForumDao;
	}



	@Override
	public List<GetForum> getAllForum() {
		String hql = "select new edu.zsc.cxl.bbs.container.GetForum(f.forumId,f.tittle,f.createTime,f.forumRul,c.tittle,a.username,f.description)"
				+ "from Forum f,Category c, Admin a where  f.createForumAdmin=a.adminId and f.category_Forum=c.categoryId";
		return getForumDao.getListByHQL(hql);
	}

}
