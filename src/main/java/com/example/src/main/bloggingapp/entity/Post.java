package com.example.src.main.bloggingapp.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore 
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="user_id")
	private User user;
	
	/*
	 * The problem with serializing a User will be that your User has many posts
	 * which point back at the same User. There are many ways to tell your JSON
	 * serializer how to handle it. Assuming you are using Jackson, the easiest
	 * might be to add the @JsonIgnore annotation on one side that you don't need
	 * serialized.
	 */	
	@OneToMany(mappedBy="post", cascade= CascadeType.ALL)
	private Set<Comment> comments= new HashSet<>();

	public Post() {
		
	}
	
	
	public Post(Integer id, String title, String content, String image, Date insertDate, Date updateDate,
			Category category, User user, Set<Comment> comments) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.image = image;
		this.insertDate = insertDate;
		this.updateDate = updateDate;
		this.category = category;
		this.user = user;
		this.comments = comments;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public Post setComments(Set<Comment> comments) {
		this.comments = comments;
		return this;
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

