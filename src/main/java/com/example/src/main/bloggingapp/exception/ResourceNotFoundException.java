package com.example.src.main.bloggingapp.exception;

import com.example.src.main.bloggingapp.entity.User;

public class ResourceNotFoundException extends RuntimeException{

	String resourceName;
	String fieldName;
	Object fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object value) {
		super(String.format("%s not found with %s: %l", resourceName, fieldName, value));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=value;
	}
	

}
