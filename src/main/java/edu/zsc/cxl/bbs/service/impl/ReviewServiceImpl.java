package edu.zsc.cxl.bbs.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.zsc.cxl.bbs.dao.ReviewDao;
import edu.zsc.cxl.bbs.entity.Review;
import edu.zsc.cxl.bbs.service.ReviewService;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

	ReviewDao reviewDao;
	
	public ReviewDao getReviewDao() {
		return reviewDao;
	}

	@Autowired
	public void setReviewDao(ReviewDao reviewDao) {
		this.reviewDao = reviewDao;
	}

	@Override
	public void saveReview(Review review) {
		reviewDao.save(review);
	}

	@Override
	public void updateReview(Review review) {
		reviewDao.update(review);
		
	}

	@Override
	public void deleteReview(Review review) {
		reviewDao.delete(review);
		
	}

	@Override
	public Review findReviewById(Serializable id) {
		
		return reviewDao.findById(id);
	}

}
