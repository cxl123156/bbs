package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="TOPIC_BUMP")
public class Bump implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true,nullable=false)
	@GenericGenerator(name="topic",strategy="foreign",parameters=@Parameter(name = "property",value="topic"))
	@GeneratedValue(generator="topic")
	private Long topicId;
	
	@ManyToOne
	@JoinColumn(name="adminId")
	private Admin bumpUser;
	
	@Column(length=200)
	private String bumpReason;
	
	@Column(length=7)
	private String tittleColor;

	@OneToOne(mappedBy="topicBump",cascade=CascadeType.ALL)
	private Topic topic;
	

	public String getBumpReason() {
		return bumpReason;
	}

	public void setBumpReason(String bumpReason) {
		this.bumpReason = bumpReason;
	}


	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public Admin getBumpUser() {
		return bumpUser;
	}

	public void setBumpUser(Admin bumpUser) {
		this.bumpUser = bumpUser;
	}

	public String getTittleColor() {
		return tittleColor;
	}

	public void setTittleColor(String tittleColor) {
		this.tittleColor = tittleColor;
	}
	
	
	
}
