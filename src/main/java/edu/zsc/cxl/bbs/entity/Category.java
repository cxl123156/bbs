package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
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
 * 分区
 * 
 */

@Entity
@Table(name="BBS_CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long categoryId;
	
	@Column(length=100)
	private String tittle;
	
	@Column(length=200)
	private String description;
	
	@OneToMany(mappedBy="category_Forum",cascade=CascadeType.ALL , fetch=FetchType.EAGER)
	private List<Forum> forumId;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime = new Date(System.currentTimeMillis());
	
	@ManyToOne
	@JoinColumn(name="createrId" , nullable=false)
	private Admin createCategoryUser; //创建者

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

	public List<Forum> getForumId() {
		return forumId;
	}

	public void setForumId(List<Forum> forumId) {
		this.forumId = forumId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Admin getCreateCategoryUser() {
		return createCategoryUser;
	}

	public void setCreateCategoryUser(Admin createCategoryUser) {
		this.createCategoryUser = createCategoryUser;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
}
