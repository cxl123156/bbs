package edu.zsc.cxl.bbs.container;

import java.io.Serializable;
import java.util.Date;

public class GetForumTopic implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String topicTittle;
	private Long forumId;
	private Long topicId;
	private String topicUser;
	private String userAvatar;
	private Date createTime;
	private String forumTittle;
	private String type;
	
	
	public GetForumTopic(String topicTittle, Long forumId, Long topicId,
			String topicUser,String userAvatar,Date createTime,String forumTittle,String type) {
		super();
		this.topicTittle = topicTittle;
		this.forumId = forumId;
		this.topicId = topicId;
		this.topicUser = topicUser;
		this.userAvatar=userAvatar;
		this.createTime=createTime;
		this.forumTittle=forumTittle;
		this.type=type;
	}
	
	
	
	
	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public Date getCreateTime() {
		return createTime;
	}



	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}



	public String getForumTittle() {
		return forumTittle;
	}



	public void setForumTittle(String forumTittle) {
		this.forumTittle = forumTittle;
	}



	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getTopicTittle() {
		return topicTittle;
	}
	public void setTopicTittle(String topicTittle) {
		this.topicTittle = topicTittle;
	}
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	public String getTopicUser() {
		return topicUser;
	}
	public void setTopicUser(String topicUser) {
		this.topicUser = topicUser;
	}

	
}
