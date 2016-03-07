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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="T_USER")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="USERID",unique=true)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	@Column(name="USERNAME", length=20 , unique=true , nullable=false)
	private String userName;
	
	@Column(name="PASSWORD",length=33 , nullable=false)
	private String password;
	
	@Column(name="EMAIL",length=30 , nullable=false)
	private String email;
	
	@Column(name="SEX",length=1)
	private int sex;
	
	@Column(name="BIRTHDAY")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthday;
	
	@Column(name="REGTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date regTime;
	
	@Column(name="LOGINIP",length=15)
	private String loginIp;
	
	@Column(name="LOGINTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date loginTime;
	
	@Column(name="LASTLOGINTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginTime;
	
	@Column(name="LASTLOGINIP",length=15)
	private String lastLoginIp;
	
	@Column(name="TOTALPOST",length=5 , columnDefinition="INT default 0")
	private int totalPost;
	
	@Column(name="TOTALESSENCE",length=5 ,columnDefinition="INT default 0")
	private int totalEssence;
	
	@Column(name="PERWEBSITE",length=20)
	private String personalWebsite;
	
	@Column(name="QQ",length=10)
	private String QQ;
	
	@Column(name="SELFASSESSMENT",length=200)
	private String selfAssessment;
	
	@Column(name="SELFSIGNATURE",length=500)
	private String selfSignature;
	
	@Column(name="AVATAR", length=50)
	private String avatar;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="reviewUser")
	private Set<Review> reviewSet;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="userLog")
	private Set<LoginLog> logSet;
	
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="postCreater")
	private Set<Post> userPost;
	
	
	@ManyToOne()
	@JoinColumn(name="groupId")
	private Group user_Group;
	
	@ManyToMany(cascade={CascadeType.ALL,CascadeType.PERSIST},fetch=FetchType.LAZY)
	@JoinTable(name="T_COLLECT",joinColumns={@JoinColumn(name="userId")} , inverseJoinColumns={@JoinColumn(name="postId")})
	private Set<Topic> topicCollect;
	
	@ManyToMany(cascade={CascadeType.ALL,CascadeType.PERSIST})
	@JoinTable(name="BBS_FORUM_USER",joinColumns={@JoinColumn(name="userId")} , inverseJoinColumns={@JoinColumn(name="forumId")})
	private Set<Forum> userForum;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Date getRegTime() {
		return regTime;
	}
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	public int getTotalEssence() {
		return totalEssence;
	}
	public void setTotalEssence(int totalEssence) {
		this.totalEssence = totalEssence;
	}

	public String getPersonalWebsite() {
		return personalWebsite;
	}
	public void setPersonalWebsite(String personalWebsite) {
		this.personalWebsite = personalWebsite;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getSelfAssessment() {
		return selfAssessment;
	}
	public void setSelfAssessment(String selfAssessment) {
		this.selfAssessment = selfAssessment;
	}
	public String getSelfSignature() {
		return selfSignature;
	}
	public void setSelfSignature(String selfSignature) {
		this.selfSignature = selfSignature;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Set<Review> getReviewSet() {
		return reviewSet;
	}
	public void setReviewSet(Set<Review> reviewSet) {
		this.reviewSet = reviewSet;
	}

	public Set<Topic> getTopicCollect() {
		return topicCollect;
	}
	public void setTopicCollect(Set<Topic> topicCollect) {
		this.topicCollect = topicCollect;
	}
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public Set<LoginLog> getLogSet() {
		return logSet;
	}
	public void setLogSet(Set<LoginLog> logSet) {
		this.logSet = logSet;
	}
	public Group getUser_Group() {
		return user_Group;
	}
	public void setUser_Group(Group user_Group) {
		this.user_Group = user_Group;
	}

	public Set<Forum> getUserForum() {
		return userForum;
	}
	public void setUserForum(Set<Forum> userForum) {
		this.userForum = userForum;
	}
	public Set<Post> getUserPost() {
		return userPost;
	}
	public void setUserPost(Set<Post> userPost) {
		this.userPost = userPost;
	}

	
	
}
