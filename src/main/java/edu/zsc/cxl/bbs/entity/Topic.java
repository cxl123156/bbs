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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/*
 * 主题
 * 
 */

@Entity
@Table(name="BBS_TOPIC")
public class Topic implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long topicId;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creatTime;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastReplyTime;
	
	@Column
	private int viewCount;
	
	@Column
	private int category;
	
	@Column
	private int postCount;
	
	@Column(length=40)
	private String tittle;

	@Column(length=8)
	private String tittleColor;
	
	@Column
	private String type;
	
	@Column
	private String cover;
	
	@Column
	private String Carousel;
	
	@OneToOne()
	@PrimaryKeyJoinColumn
	private Delete topicDelete;

	@OneToOne(cascade=CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private HighLight topicHighLight;
	
    @OneToOne()
	@PrimaryKeyJoinColumn
	private Bump topicBump;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="topicId",fetch=FetchType.EAGER)
	private Set<Post> postId;
	
	@ManyToOne()
	@JoinColumn(name="forumId")
	private Forum forumId;
	
	@ManyToOne()
	@JoinColumn(name="createrId")
	private User createrId;
	
	@ManyToMany(mappedBy="topicCollect")
	private Set<User> userCollect;

	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public Date getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getPostCount() {
		return postCount;
	}

	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getTittleColor() {
		return tittleColor;
	}

	public void setTittleColor(String tittleColor) {
		this.tittleColor = tittleColor;
	}

	public Delete getTopicDelete() {
		return topicDelete;
	}

	public void setTopicDelete(Delete topicDelete) {
		this.topicDelete = topicDelete;
	}

	public HighLight getTopicHighLight() {
		return topicHighLight;
	}

	public void setTopicHighLight(HighLight topicHighLight) {
		this.topicHighLight = topicHighLight;
	}

	public Bump getTopicBump() {
		return topicBump;
	}

	public void setTopicBump(Bump topicBump) {
		this.topicBump = topicBump;
	}

	public Set<Post> getPostId() {
		return postId;
	}

	public void setPostId(Set<Post> postId) {
		this.postId = postId;
	}

	public Forum getForumId() {
		return forumId;
	}

	public void setForumId(Forum forumId) {
		this.forumId = forumId;
	}

	public User getCreaterId() {
		return createrId;
	}

	public void setCreaterId(User createrId) {
		this.createrId = createrId;
	}

	public Set<User> getUserCollect() {
		return userCollect;
	}

	public void setUserCollect(Set<User> userCollect) {
		this.userCollect = userCollect;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getCarousel() {
		return Carousel;
	}

	public void setCarousel(String carousel) {
		Carousel = carousel;
	}
	
	
	
}
