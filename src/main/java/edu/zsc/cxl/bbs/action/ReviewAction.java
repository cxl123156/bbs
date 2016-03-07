package edu.zsc.cxl.bbs.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.mysql.fabric.xmlrpc.base.Data;

import edu.zsc.cxl.bbs.entity.Post;
import edu.zsc.cxl.bbs.entity.Review;
import edu.zsc.cxl.bbs.entity.ReviewText;
import edu.zsc.cxl.bbs.entity.User;
import edu.zsc.cxl.bbs.service.PostService;
import edu.zsc.cxl.bbs.service.ReviewService;

public class ReviewAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Review review;
	private ReviewService reviewService;
	private PostService postService;
	private ReviewText reviewText;
	private Long postId;


	public ReviewText getReviewText() {
		return reviewText;
	}
	public void setReviewText(ReviewText reviewText) {
		this.reviewText = reviewText;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	@Autowired
	public void setPostService(PostService postService) {
		this.postService = postService;
	}
	public Review getReview() {
		return review;
	}
	public void setReview(Review review) {
		this.review = review;
	}
	@Autowired
	public void setReviewService(ReviewService reviewService) {
		this.reviewService = reviewService;
	}
	
	public String createReview(){
		Date reviewTime = new Date();
		String ip = getRequest().getRemoteAddr();
		User user = (User) getSession().getAttribute("currentUser");
		Post post = postService.findPostById(postId);
		Review r = new Review();
		reviewText.setReviewTime(reviewTime);
		reviewText.setReviewIp(ip);
		r.setReviewText(reviewText);
		r.setReviewPost(post);
		if(user.getUserId().equals(post.getPostCreater().getUserId()))
		{
			r.setReviewUser(post.getPostCreater());
		}
		else{
			r.setReviewUser(user);
		}
		System.out.println("回复内容："+reviewText.getText());
		reviewText.setReview(r);
		reviewService.saveReview(r);
		return "createReview";
	}
	
	

}
