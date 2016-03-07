package edu.zsc.cxl.bbs.service;

import java.io.Serializable;

import edu.zsc.cxl.bbs.entity.PostText;

public interface PostTextService {

	public void savePostText(PostText postText);
	public PostText findPostTextById(Serializable id);
}
