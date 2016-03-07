package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/*
 * 帖子信息
 * 
 */
@Entity
@Table(name="BBS_POST")
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long postId;
	
	@Column
	private Long editerId;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;
	
	@Column(length=15)
    private String createrIp;
	
	@Column(length=15)
    private String editerIp;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private Date editTime;
	
	@Column
    private int editCount;
	
	@Column
    private int readTime;
	
	@Column
    private int replyCount;
	
	@Column
	private int indexCount;//楼层
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
    private Date newerReplyTime;
	
	@Column
    private int status;
	
	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Delete postDelete;
	
	@OneToOne(cascade=CascadeType.ALL)  
	@PrimaryKeyJoinColumn  
	private PostText postText;
    
    @ManyToOne()
    @JoinColumn(name="topicId")
    private Topic topicId;
    
	@ManyToOne()
	@JoinColumn(name="createrId")
	private User postCreater;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="reviewPost")
	private Set<Review> reviewSet;
	
	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public Long getEditerId() {
		return editerId;
	}

	public void setEditerId(Long editerId) {
		this.editerId = editerId;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreaterIp() {
		return createrIp;
	}

	public void setCreaterIp(String createrIp) {
		this.createrIp = createrIp;
	}

	public String getEditerIp() {
		return editerIp;
	}

	public void setEditerIp(String editerIp) {
		this.editerIp = editerIp;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	public int getEditCount() {
		return editCount;
	}

	public void setEditCount(int editCount) {
		this.editCount = editCount;
	}

	public int getReadTime() {
		return readTime;
	}

	public void setReadTime(int readTime) {
		this.readTime = readTime;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public Date getNewerReplyTime() {
		return newerReplyTime;
	}

	public void setNewerReplyTime(Date newerReplyTime) {
		this.newerReplyTime = newerReplyTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public PostText getPostText() {
		return postText;
	}

	public void setPostText(PostText postText) {
		this.postText = postText;
	}

	public User getPostCreater() {
		return postCreater;
	}

	public void setPostCreater(User postCreater) {
		this.postCreater = postCreater;
	}

	public Set<Review> getReviewSet() {
		return reviewSet;
	}

	public void setReviewSet(Set<Review> reviewSet) {
		this.reviewSet = reviewSet;
	}

	public Delete getPostDelete() {
		return postDelete;
	}

	public void setPostDelete(Delete postDelete) {
		this.postDelete = postDelete;
	}

	public Topic getTopicId() {
		return topicId;
	}

	public void setTopicId(Topic topicId) {
		this.topicId = topicId;
	}

	public int getIndexCount() {
		return indexCount;
	}

	public void setIndexCount(int indexCount) {
		this.indexCount = indexCount;
	}


	
}
