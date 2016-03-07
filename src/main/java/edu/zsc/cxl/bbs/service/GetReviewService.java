package edu.zsc.cxl.bbs.service;

import java.io.Serializable;
import java.util.List;

import edu.zsc.cxl.bbs.container.GetReview;

public interface GetReviewService {

	public List<GetReview> getAllReview(Serializable postId);
	
}
