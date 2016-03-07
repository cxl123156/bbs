package edu.zsc.cxl.bbs.container;

import java.io.Serializable;
import java.util.Date;

public class GetTopic implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tittle;
	private String createUser;
	private Date createTime;
	private Date lastReplyTime;
	private int viewCount;
	private String tittleColor;
	private String userAvatar;
	private String forumTittle;
	private String postText;
	private Long totalResult;
	private Long topicId;
	
	public GetTopic(String tittle, String createUser,int viewCount,String tittleColor,Date createTime,Long topicId){
		this.tittle=tittle;
		this.createUser = createUser;
		this.createTime = createTime;
		this.viewCount = viewCount;
		this.tittleColor = tittleColor;
		this.topicId = topicId;
	}

	public GetTopic(Long totalResult){
		this.totalResult = totalResult;
	}
	
	public GetTopic(String tittle, String createUser,  int viewCount, String tittleColor,
			String userAvatar, String forumTittle,Date createTime,
			Date lastReplyTime,Long topicId) {
		super();
		this.tittle = tittle;
		this.createUser = createUser;
		this.createTime = createTime;
		this.lastReplyTime = lastReplyTime;
		this.viewCount = viewCount;
		this.tittleColor = tittleColor;
		this.userAvatar = userAvatar;
		this.forumTittle = forumTittle;
		this.topicId = topicId;
	}



	
	
	
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getTotalResult() {
		return totalResult;
	}



	public void setTotalResult(Long totalResult) {
		this.totalResult = totalResult;
	}



	public String getForumTittle() {
		return forumTittle;
	}
	public void setForumTittle(String forumTittle) {
		this.forumTittle = forumTittle;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getTittleColor() {
		return tittleColor;
	}
	public void setTittleColor(String tittleColor) {
		this.tittleColor = tittleColor;
	}
	public String getUserAvatar() {
		return userAvatar;
	}
	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}
	public String getPostText() {
		return postText;
	}
	public void setPostText(String postText) {
		this.postText = postText;
	}
	
	
	

}
