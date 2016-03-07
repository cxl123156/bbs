package edu.zsc.cxl.bbs.container;

import java.io.Serializable;
import java.util.Date;

public class GetForum implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long forumId;
	private String forumTittle;
	private Date createTime;
	private String forumRul;
	private String categoryTittle;
	private String createAdmin;
	private String description;
	
	
	public GetForum(Long forumId, String forumTittle, Date createTime, String forumRul, String categoryTittle , String createAdmin , String description) {
		super();
		this.forumId = forumId;
		this.forumTittle = forumTittle;
		this.createTime = createTime;
		this.forumRul = forumRul;
		this.categoryTittle = categoryTittle;
		this.createAdmin = createAdmin;
		this.description = description;
	}
	
	
	
	public String getCreateAdmin() {
		return createAdmin;
	}



	public void setCreateAdmin(String createAdmin) {
		this.createAdmin = createAdmin;
	}



	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	public String getForumTittle() {
		return forumTittle;
	}
	public void setForumTittle(String forumTittle) {
		this.forumTittle = forumTittle;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCategoryTittle() {
		return categoryTittle;
	}
	public void setCategoryTittle(String categoryTittle) {
		this.categoryTittle = categoryTittle;
	}



	public String getForumRul() {
		return forumRul;
	}



	public void setForumRul(String forumRul) {
		this.forumRul = forumRul;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
