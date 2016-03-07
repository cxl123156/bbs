package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.container.GetForumTopic;
import edu.zsc.cxl.bbs.container.PageModel;

public interface GetForumTopicService {

	public List<GetForumTopic> getAllTopicForum(Serializable forumId,PageModel<GetForumTopic> pm);
}
