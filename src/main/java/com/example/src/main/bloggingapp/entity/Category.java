package com.example.src.main.bloggingapp.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="category_id")
	Integer Id;
	
	@Column(name="category_title")
	String title;
	
	@Column(name="category_description")
	String Description;

	@OneToMany(mappedBy="category", cascade= CascadeType.ALL)
	private List<Post> posts= new ArrayList<>();
	
	public Category() {
		//Empty Constructor
	}
	
	public Integer getId() {
		return Id;
	}

	public Category setId(Integer id) {
		Id = id;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Category setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getDescription() {
		return Description;
	}

	public Category setDescription(String description) {
		Description = description;
		return this;
	}
	
	

}
