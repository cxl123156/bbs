package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name="TOPIC_DELETE")
public class Delete implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true,nullable=false)
	@GenericGenerator(name="topic",strategy="foreign",parameters=@Parameter(name = "property",value="topic"))
	@GeneratedValue(generator="topic")
	private Long topicId;
	
	@Column
	public Long userId;
	
	@Column(length=200)
	private String deleteReason;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date deleteTime;
	
	@OneToOne(mappedBy="topicDelete",cascade=CascadeType.ALL)
	private Topic topic;



	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDeleteReason() {
		return deleteReason;
	}

	public void setDeleteReason(String deleteReason) {
		this.deleteReason = deleteReason;
	}

	public Date getDeleteTime() {
		return deleteTime;
	}

	public void setDeleteTime(Date deleteTime) {
		this.deleteTime = deleteTime;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}


	
	
	
}

