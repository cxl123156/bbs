package edu.zsc.cxl.bbs.service;


import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.entity.Post;

public interface PostService {

	public void savePost(Post post);
	public void updatePost(Post post);
	public void deletePost(Post post);
	public Post findPostById(Serializable id);
	public Post findPostByTopic(Serializable id);
}
