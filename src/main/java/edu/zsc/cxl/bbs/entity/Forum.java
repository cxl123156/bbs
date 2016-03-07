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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 *  板块
 * 
 */

@Entity
@Table(name="BBS_FORUM")
public class Forum implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long forumId;
	
	@Column
	private Long lastPostId;//最后回帖
	
	@Column
	private Long lastUserId;//最后回帖人
	
	@Column(length=20)
	private String tittle;
	
	@Column(length=150)
	private String description;
	
	@Column(length=500)
	private String forumRul; //版规
	
	@Column
	private int topicTotal;
	
	@Column
	private int postTotal;
	
	@Column
	private int postToday;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date(System.currentTimeMillis());
	
	@OneToMany(mappedBy="forumId",cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	private Set<Topic> topicId;
	
	@ManyToOne()
	@JoinColumn(name="categoryId")
	private Category category_Forum;


	
	@ManyToOne
	@JoinColumn(name="adminId")
	private Admin createForumAdmin;
	
	@ManyToMany(mappedBy="userForum",cascade = { CascadeType.ALL})
	private Set<User> forumUser; //版主
	
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}

	public Long getLastPostId() {
		return lastPostId;
	}

	public void setLastPostId(Long lastPostId) {
		this.lastPostId = lastPostId;
	}

	public Long getLastUserId() {
		return lastUserId;
	}

	public void setLastUserId(Long lastUserId) {
		this.lastUserId = lastUserId;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getForumRul() {
		return forumRul;
	}

	public void setForumRul(String forumRul) {
		this.forumRul = forumRul;
	}

	public int getTopicTotal() {
		return topicTotal;
	}

	public void setTopicTotal(int topicTotal) {
		this.topicTotal = topicTotal;
	}

	public int getPostTotal() {
		return postTotal;
	}

	public void setPostTotal(int postTotal) {
		this.postTotal = postTotal;
	}

	public int getPostToday() {
		return postToday;
	}

	public void setPostToday(int postToday) {
		this.postToday = postToday;
	}

	public Set<Topic> getTopicId() {
		return topicId;
	}

	public void setTopicId(Set<Topic> topicId) {
		this.topicId = topicId;
	}

	public Category getCategory_Forum() {
		return category_Forum;
	}

	public void setCategory_Forum(Category category_Forum) {
		this.category_Forum = category_Forum;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Set<User> getForumUser() {
		return forumUser;
	}

	public void setForumUser(Set<User> forumUser) {
		this.forumUser = forumUser;
	}

	public Admin getCreateForumAdmin() {
		return createForumAdmin;
	}

	public void setCreateForumAdmin(Admin createForumAdmin) {
		this.createForumAdmin = createForumAdmin;
	}


}