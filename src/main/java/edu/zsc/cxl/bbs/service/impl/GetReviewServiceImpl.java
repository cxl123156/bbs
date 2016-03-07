package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.container.GetReview;
import edu.zsc.cxl.bbs.dao.GetReviewDao;
import edu.zsc.cxl.bbs.service.GetReviewService;

@Service("getReviewService")
public class GetReviewServiceImpl implements GetReviewService {

	GetReviewDao getReviewDao;
	
	
	public GetReviewDao getGetReviewDao() {
		return getReviewDao;
	}

	@Autowired
	public void setGetReviewDao(GetReviewDao getReviewDao) {
		this.getReviewDao = getReviewDao;
	}


	@Override
	public List<GetReview> getAllReview(Serializable postId) {
		String hql = "select new edu.zsc.cxl.bbs.container.GetReview(rt.text,u.userName,r.reviewId,p.postId,rt.reviewTime)"
				+ " from Review r,ReviewText rt,Post p,User u where p.postId="+postId+" and p.postId=r.reviewPost and r.reviewUser=u.userId and rt.reviewId=r.reviewId";
		return getReviewDao.getListByHQL(hql);
	}

}
