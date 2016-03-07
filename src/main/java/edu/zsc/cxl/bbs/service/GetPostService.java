package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.container.GetPost;
import edu.zsc.cxl.bbs.container.PageModel;

public interface GetPostService {

	public List<GetPost> showAllPost(PageModel<GetPost> pm , Serializable topicId);
}
