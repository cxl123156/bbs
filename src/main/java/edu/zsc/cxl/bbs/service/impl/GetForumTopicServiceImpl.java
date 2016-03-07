package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.container.GetForumTopic;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.dao.GetForumTopicDao;
import edu.zsc.cxl.bbs.service.GetForumTopicService;

@Service("getForumTopicService")
public class GetForumTopicServiceImpl implements GetForumTopicService {

	GetForumTopicDao getForumTopicDao;
	
	public GetForumTopicDao getGetForumTopicDao() {
		return getForumTopicDao;
	}

	@Autowired
	public void setGetForumTopicDao(GetForumTopicDao getForumTopicDao) {
		this.getForumTopicDao = getForumTopicDao;
	}

	@Override
	public List<GetForumTopic> getAllTopicForum(Serializable forumId,PageModel<GetForumTopic> pm) {
		String hql = "select new edu.zsc.cxl.bbs.container.GetForumTopic(t.tittle,f.forumId,t.topicId,u.userName,u.avatar,t.creatTime,f.tittle,t.type) "
				+ "from Topic t,Forum f,User u where f.forumId="+forumId+" and t.forumId=f.forumId and u.userId=t.createrId";		
		return getForumTopicDao.findInPageByHQL(hql, pm);
	}

}
