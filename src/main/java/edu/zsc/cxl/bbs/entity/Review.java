package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="BBS_REVIEW")
public class Review implements Serializable {

	private static final long serialVersionUID = 3L;
	@Id
	@Column(unique=true,nullable=false)
	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long reviewId;
	
	@OneToOne(cascade=CascadeType.ALL)  
	@PrimaryKeyJoinColumn  
	private ReviewText reviewText;
	
	@ManyToOne
	@JoinColumn(name="postId")
	private Post reviewPost;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User reviewUser;
	
	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public ReviewText getReviewText() {
		return reviewText;
	}

	public void setReviewText(ReviewText reviewText) {
		this.reviewText = reviewText;
	}

	public Post getReviewPost() {
		return reviewPost;
	}

	public void setReviewPost(Post reviewPost) {
		this.reviewPost = reviewPost;
	}

	public User getReviewUser() {
		return reviewUser;
	}

	public void setReviewUser(User reviewUser) {
		this.reviewUser = reviewUser;
	}

	
	
}
