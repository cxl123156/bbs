package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.entity.Forum;

public interface ForumService {
	public void saveForum(Forum forum);
	public void updateForum(Forum forum);
	public void deleteForum(Forum forum);
	public Forum findForumByTittle(String tittle);
	public Forum findForumById(Serializable id);
	public List<Forum> findForumBycategory(Serializable id);
	public void setCreater(Serializable createrId, Serializable tittle);
	public List<Forum> findAllForum();

}
