package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="T_ADMIN")
public class Admin implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true,nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long adminId;
	
	@Column(length=10)
	private String username;
	
	@Column(length=33)
	private String password;

	@OneToMany(cascade=CascadeType.ALL,mappedBy="createCategoryUser" )
	private Set<Category> categoryCreateUser;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="createForumAdmin")
	private Set<Forum> forumCreateAdmin;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="highLightAdmin")
	private Set<HighLight> highLight;
	
	public Set<Forum> getForumCreateAdmin() {
		return forumCreateAdmin;
	}

	public void setForumCreateAdmin(Set<Forum> forumCreateAdmin) {
		this.forumCreateAdmin = forumCreateAdmin;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Category> getCategoryCreateUser() {
		return categoryCreateUser;
	}

	public void setCategoryCreateUser(Set<Category> categoryCreateUser) {
		this.categoryCreateUser = categoryCreateUser;
	}

	public Set<HighLight> getHighLight() {
		return highLight;
	}

	public void setHighLight(Set<HighLight> highLight) {
		this.highLight = highLight;
	}

	
	

}
