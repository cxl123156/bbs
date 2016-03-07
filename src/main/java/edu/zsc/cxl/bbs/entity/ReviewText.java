package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="REVIEW_REVIEWTEXT")
public class ReviewText implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(unique=true,nullable=false)
	@GeneratedValue(generator="review")
	@GenericGenerator(name="review",strategy="foreign",parameters=@Parameter(name = "property", value = "review"))
	private Long reviewId;
	
	@Column()
	private String text;
	
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	private Date editTime;
	
	@Column()
	@Temporal(TemporalType.TIMESTAMP)
	private Date reviewTime;
	
	@Column(length=15)
	private String reviewIp;

	@OneToOne(mappedBy="reviewText")
	private Review review;
	
	public Long getReviewId() {
		return reviewId;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}



	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Review getReview() {
		return review;
	}

	public void setReview(Review review) {
		this.review = review;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public Date getReviewTime() {
		return reviewTime;
	}

	public void setReviewTime(Date reviewTime) {
		this.reviewTime = reviewTime;
	}

	public String getReviewIp() {
		return reviewIp;
	}

	public void setReviewIp(String reviewIp) {
		this.reviewIp = reviewIp;
	}
	
	
}
