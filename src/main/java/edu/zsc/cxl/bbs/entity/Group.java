package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * 用户组
 */
@Entity
@Table(name="BBS_USERGROUP")
public class Group implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable=false,unique=true)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long groupId;
	
	@Column(nullable=false,unique=true)
	private String groupName;
	
	@Column
	private String groupImg;
	
	@OneToMany(mappedBy="user_Group", cascade=CascadeType.MERGE)
	private Set<User> groupSet;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL,CascadeType.PERSIST})
	@JoinTable(name="BBS_FORUMGROUP_TOPIC",joinColumns={@JoinColumn(name="groupId")} , inverseJoinColumns={@JoinColumn(name="forumId")})
	private Set<Forum> forumGroupTopic;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL,CascadeType.PERSIST})
	@JoinTable(name="BBS_FORUMGROUP_REPLY",joinColumns={@JoinColumn(name="groupId")} , inverseJoinColumns={@JoinColumn(name="forumId")})
	private Set<Forum> forumGroupReply;
	
	@ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL,CascadeType.PERSIST})
	@JoinTable(name="BBS_FORUMGROUP_VIEW",joinColumns={@JoinColumn(name="groupId")} , inverseJoinColumns={@JoinColumn(name="forumId")})
	private Set<Forum> forumGroupVIEW;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupImg() {
		return groupImg;
	}

	public void setGroupImg(String groupImg) {
		this.groupImg = groupImg;
	}

	public Set<Forum> getForumGroupTopic() {
		return forumGroupTopic;
	}

	public void setForumGroupTopic(Set<Forum> forumGroupTopic) {
		this.forumGroupTopic = forumGroupTopic;
	}

	public Set<Forum> getForumGroupReply() {
		return forumGroupReply;
	}

	public void setForumGroupReply(Set<Forum> forumGroupReply) {
		this.forumGroupReply = forumGroupReply;
	}

	public Set<Forum> getForumGroupVIEW() {
		return forumGroupVIEW;
	}

	public void setForumGroupVIEW(Set<Forum> forumGroupVIEW) {
		this.forumGroupVIEW = forumGroupVIEW;
	}

	public Set<User> getGroupSet() {
		return groupSet;
	}

	public void setGroupSet(Set<User> groupSet) {
		this.groupSet = groupSet;
	}
	
}
