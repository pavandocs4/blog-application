package com.example.src.main.bloggingapp.dto;

public class CategoryDTO {
	
	Integer id;
	String title;
	String description;
	
	public CategoryDTO() {	
	}
	
	public CategoryDTO(Integer id, String title, String description) {	
		this.id=id;
		this.title=title;
		this.description=description;
	}
	
	
	public Integer getId() {
		return id;
	}
	public CategoryDTO setId(Integer id) {
		this.id = id;
		return this;
	}
	public String getTitle() {
		return title;
	}
	public CategoryDTO setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public CategoryDTO setDescription(String description) {
		this.description = description;
		return this;
	}
	
	
}
