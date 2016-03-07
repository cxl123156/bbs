package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.container.GetPost;
import edu.zsc.cxl.bbs.container.PageModel;
import edu.zsc.cxl.bbs.dao.GetPostDao;
import edu.zsc.cxl.bbs.entity.Post;
import edu.zsc.cxl.bbs.service.GetPostService;

@Service("getPostService")
public class GetPostServiceImpl implements GetPostService {

	private GetPostDao getPostDao;
	
	
	public GetPostDao getGetPostDao() {
		return getPostDao;
	}

	@Autowired
	public void setGetPostDao(GetPostDao getPostDao) {
		this.getPostDao = getPostDao;
	}



	@Override
	public List<GetPost> showAllPost(PageModel<GetPost> pm , Serializable topicId) {

			String hql="select new edu.zsc.cxl.bbs.container.GetPost(p.postId,pt.text,p.creatTime,u.userName,t.tittle,u.avatar) from Post p,PostText pt,User u,Topic t "
					+ "where t.topicId="+topicId+" and p.topicId="+topicId+" and p.postId=pt.postId and p.postCreater=u.userId order by p.creatTime asc";
			List<GetPost> list =getPostDao.findInPageByHQL(hql, pm);
			return list;

	}

}
