package edu.zsc.cxl.bbs.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
/*
 * 帖子内容
 * 
 */
@Entity
@Table(name="POST_POSTTEXT")
public class PostText implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column
	@GenericGenerator(name="post",strategy="foreign",parameters={@Parameter(name = "property",value="post")})
	@GeneratedValue(generator="post")
	private Long postId;
	
	@Column()
	private String text;
	
	@Column(length=100)
	private String tittle;

	@OneToOne(mappedBy="postText")
	private Post post;
	
	public Long getPostId() {
		return postId;
	}

	public void setPostId(Long postId) {
		this.postId = postId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}


	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	
}
