package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.ForumDao;
import edu.zsc.cxl.bbs.entity.Forum;
import edu.zsc.cxl.bbs.service.ForumService;

@Service("forumService")
public class ForumServiceImpl implements ForumService {
	
	ForumDao forumDao;

	
	public ForumDao getForumDao() {
		return forumDao;
	}

	@Autowired
	public void setForumDao(ForumDao forumDao) {
		this.forumDao = forumDao;
	}

	@Override
	public void saveForum(Forum forum) {
		forumDao.save(forum);
	}

	@Override
	public void updateForum(Forum forum) {
		forumDao.update(forum);
	}

	@Override
	public void deleteForum(Forum forum) {
		forumDao.delete(forum);
	}

	@Override
	public Forum findForumByTittle(String tittle) {
		String hql = "from Forum where tittle = ?";
		return forumDao.getByHQL(hql, tittle);
	}

	@Override
	public Forum findForumById(Serializable id) {
		return forumDao.findById(id);
	}

	@Override
	public List<Forum> findForumBycategory(Serializable id) {
		String hql = "from Forum where categoryId = ?";
		return forumDao.getListByHQL(hql, id);
	}

	@Override
	public void setCreater(Serializable createrId , Serializable forumId) {
		String sql = "update BBS_FORUM set createrId ="+ createrId + "where forumId = "+forumId+";";
		
		forumDao.insertBySQL(sql);
	}

	@Override
	public List<Forum> findAllForum() {
		String hql = "from Forum ";
		return forumDao.getListByHQL(hql);
	}


}
