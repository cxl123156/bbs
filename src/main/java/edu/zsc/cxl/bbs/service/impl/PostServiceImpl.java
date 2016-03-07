package edu.zsc.cxl.bbs.service.impl;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.dao.PostDao;
import edu.zsc.cxl.bbs.dao.baseDao;
import edu.zsc.cxl.bbs.entity.Post;
import edu.zsc.cxl.bbs.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {

	PostDao postDao;
	
	
	public PostDao getPostDao() {
		return postDao;
	}



	@Autowired
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}



	@Override
	public void savePost(Post post) {
		 postDao.save(post);

	}

	@Override
	public Post findPostById(Serializable id) {
		
		return postDao.findById(id);
	}



	@Override
	public void updatePost(Post post) {
		postDao.update(post);
		
	}



	@Override
	public void deletePost(Post post) {
		postDao.delete(post);
		
	}



	@Override
	public Post findPostByTopic(Serializable id) {
		System.out.println(id);
		String hql = "from Post p "
				+ "where p.topicId="+id;
		return postDao.getByHQL(hql);
	}








}
