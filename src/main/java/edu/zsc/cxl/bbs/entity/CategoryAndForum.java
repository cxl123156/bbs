package edu.zsc.cxl.bbs.entity;

import java.util.Date;

public class CategoryAndForum {

	private String tittle;
	private String createUser;
	private Date createTime;
	private Date lastReplyTime;
	private int viewCount;
	private String tittleColor;
	private String userAvatar;
	private String forumTittle;
	private String postText;
	

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
