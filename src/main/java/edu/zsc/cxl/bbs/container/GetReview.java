package edu.zsc.cxl.bbs.container;

import java.io.Serializable;
import java.util.Date;

public class GetReview implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String reviewText;
	private String createUser;
	private Long reviewId;
	private Long postId;
	private Date createTime;
	
	
	
	public GetReview(String reviewText, String createUser, Long reviewId,
			Long postId, Date createTime) {
		super();
		this.reviewText = reviewText;
		this.createUser = createUser;
		this.reviewId = reviewId;
		this.postId = postId;
		this.createTime = createTime;
	}
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Long getReviewId() {
		return reviewId;
	}
	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
	

}
