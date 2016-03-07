package edu.zsc.cxl.bbs.container;

import java.io.Serializable;
import java.util.Date;

public class GetCategory implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long categoryId;
	private String tittle;
	private Date createTime;
	private String createUser;
	private String describe;

	public GetCategory(Long categoryId, String tittle, Date createTime,
			String createUser, String describe) {
		super();
		this.categoryId = categoryId;
		this.tittle = tittle;
		this.createTime = createTime;
		this.createUser = createUser;
		this.describe = describe;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
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
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	} 
	
	
}
