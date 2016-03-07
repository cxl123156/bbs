package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.container.GetTopic;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.dao.GetTopicDao;
import edu.zsc.cxl.bbs.service.GetTopicService;

@Service("getTopicService")
public class GetTopicServiceImpl implements GetTopicService {

	GetTopicDao getTopicDao;
	
	public GetTopicDao getGetTopicDao() {
		return getTopicDao;
	}

	@Autowired
	public void setGetTopicDao(GetTopicDao getTopicDao) {
		this.getTopicDao = getTopicDao;
	}

	@Override
	public List<GetTopic> findTopicInPage(PageModel<GetTopic> pm) {
		String hql = "select new edu.zsc.cxl.bbs.container.GetTopic(t.tittle,u.userName,t.viewCount,t.tittleColor,u.avatar,f.tittle,t.creatTime,t.lastReplyTime,t.topicId) "
				+ "from Topic t,User u,Forum f where t.forumId=f.forumId and t.createrId=u.userId ";
		return getTopicDao.findInPageByHQL(hql , pm);
	}

	@Override
	public Long getTotalRecords() {
		String hql = "select new edu.zsc.cxl.bbs.container.GetTopic(count(*)) from Topic t,User u,Post p,PostText pt,Forum f where t.forumId=f.forumId and t.createrId=u.userId and p.topicId=t.topicId and p.postId=pt.postId" ;
		GetTopic gt = getTopicDao.getByHQL(hql);
		return gt.getTotalResult();
	}

	@Override
	public GetTopic getTopicById(Serializable topicId) {
		String hql = "select new edu.zsc.cxl.bbs.container.GetTopic(t.tittle,u.userName,t.viewCount,t.tittleColor,u.avatar,f.tittle,t.creatTime,t.lastReplyTime,t.topicId) "
				+ "from Topic t,User u,Forum f "
				+ "where t.topicId="+topicId+" and t.forumId=f.forumId and t.createrId=u.userId" ;
		return getTopicDao.getByHQL(hql);
	}

	@Override
	public List<GetTopic> getNewTopic(PageModel<GetTopic> pm) {
		String hql = "select new edu.zsc.cxl.bbs.container.GetTopic(t.tittle,u.userName,t.viewCount,t.tittleColor,t.creatTime,t.topicId)"
				+ "from Topic t , User u where t.createrId=u.userId order by t.creatTime desc";
		return getTopicDao.findInPageByHQL(hql, pm);
	}

	@Override
	public List<GetTopic> getHotTopic(PageModel<GetTopic> pm) {
		String hql = "select new edu.zsc.cxl.bbs.container.GetTopic(t.tittle,u.userName,t.viewCount,t.tittleColor,t.creatTime,t.topicId)"
				+ "from Topic t , User u where t.createrId=u.userId order by t.viewCount desc";
		return getTopicDao.findInPageByHQL(hql, pm);
	}

	@Override
	public List<GetTopic> findTopicByForum(Serializable forumId,
			PageModel<GetTopic> pm) {
		String hql="select new edu.zsc.cxl.bbs.container.GetTopic(t.tittle, u.createUser,  t.viewCount, t.tittleColor,u.userAvatar, f.forumTittle,t.createTime,t.lastReplyTime,t.topicId) "
				+ "from Topic t,User u,Forum f "
				+ "where t.topicId=topicId and t.forumId="+forumId+" and t.createrId=u.userId";
		return getTopicDao.findInPageByHQL(hql, pm);
	}

}
