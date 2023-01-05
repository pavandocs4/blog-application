package com.example.src.main.bloggingapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class Post {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="post_id")
	private Integer id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(length=1000)
	private String content;
	
	private String image;
	
	@Column(name="audit_insert_date")
	private Date insertDate;
	
	@Column(name="audit_update_date")
	private Date updateDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	public Post() {
		
	}
	
	public Post(Integer id, String title, String content, String image, Date insertDate, Date updateDate,
			Category category, User user) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
		this.category = category;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public Post setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Post setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Post setContent(String content) {
		this.content = content;
		return this;
	}

	public String getImage() {
		return image;
	}

	public Post setImage(String image) {
		this.image = image;
		return this;
	}

	public Date getInsertDate() {
		return insertDate;
	}

	public Post setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
		return this;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public Post setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
		return this;
	}

	public Category getCategory() {
		return category;
	}

	public Post setCategory(Category category) {
		this.category = category;
		return this;
	}

	public User getUser() {
		return user;
	}

	public Post setUser(User user) {
		this.user = user;
		return this;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", image=" + image + ", insertDate="
				+ insertDate + ", updateDate=" + updateDate + ", category=" + category + ", user=" + user + "]";
	}

}

