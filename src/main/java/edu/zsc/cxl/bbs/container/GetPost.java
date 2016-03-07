package edu.zsc.cxl.bbs.container;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chen
 *
 */
public class GetPost implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long postId;
	private String postText;
	private Date createTime;
	private String createUser;
	private String topicTittle;
	private String avatar;
		
	
	public GetPost(Long postId, String postText, Date createTime,
			String createUser,String topicTittle,String avatar) {
		super();
		this.postId = postId;
		this.postText = postText;
		this.createTime = createTime;
		this.createUser = createUser;
		this.topicTittle=topicTittle;
		this.avatar=avatar;
	}
	
	
	
	public String getTopicTittle() {
		return topicTittle;
	}



	public void setTopicTittle(String topicTittle) {
		this.topicTittle = topicTittle;
	}



	public String getAvatar() {
		return avatar;
	}



	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}



	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}
	public String getPostText() {
		return postText;
	}
	public void setPostText(String postText) {
		this.postText = postText;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
	

}
