package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.container.PageModel;

public interface GetTopicService {

	public List<GetTopic> findTopicInPage(PageModel<GetTopic> pm);
	public Long getTotalRecords();
	public GetTopic getTopicById(Serializable topicId);
	public List<GetTopic> getNewTopic(PageModel<GetTopic> pm);
	public List<GetTopic> getHotTopic(PageModel<GetTopic> pm);
	public List<GetTopic> findTopicByForum(Serializable forumId,PageModel<GetTopic> pm);
}
