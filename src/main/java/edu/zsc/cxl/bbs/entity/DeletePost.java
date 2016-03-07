package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
/*
 * 删除帖子
 * 
 */

@Entity
@Table(name="POST_DELETE")
public class DeletePost implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true,nullable=false)
	@GenericGenerator(name="post",strategy="foreign",parameters=@Parameter(name = "property",value="post"))
	@GeneratedValue(generator="post")
	private Long postId;
	
	@Column
	public Long userId;
	
	@Column(length=200)
	private String deleteReason;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteTime;
	
	@OneToOne(mappedBy="postDelete",cascade=CascadeType.ALL)
	private Post post;

	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}

