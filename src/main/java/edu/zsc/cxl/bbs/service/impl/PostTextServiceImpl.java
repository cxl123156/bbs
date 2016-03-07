package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.PostTextDao;
import edu.zsc.cxl.bbs.entity.PostText;
import edu.zsc.cxl.bbs.service.PostTextService;

@Service("postTextService")
public class PostTextServiceImpl implements PostTextService {

	PostTextDao postTextDao;
	
	public PostTextDao getPostTextDao() {
		return postTextDao;
	}


	@Autowired
	public void setPostTextDao(PostTextDao postTextDao) {
		this.postTextDao = postTextDao;
	}


	@Override
	public void savePostText(PostText postText) {
		// TODO Auto-generated method stub
		postTextDao.save(postText);
	}


	@Override
	public PostText findPostTextById(Serializable id) {
		return postTextDao.findById(id);
	}

}
