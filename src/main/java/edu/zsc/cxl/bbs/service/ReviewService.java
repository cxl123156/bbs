package edu.zsc.cxl.bbs.service;

import java.io.Serializable;

import edu.zsc.cxl.bbs.entity.Review;

public interface ReviewService {

	public void saveReview(Review review);
	public void updateReview(Review review);
	public void deleteReview(Review review);
	public Review findReviewById(Serializable id);
}
