package com.example.src.main.bloggingapp.exception;


public class ResourceAlreadyPresentException extends RuntimeException{
	private static final long serialVersionUID = 3562327697141287497L;
	String resourceName;
	String fieldName;
	Object fieldValue;
	
	public ResourceAlreadyPresentException(String resourceName, String fieldName, Object value) {
		super(String.format("%s already present with %s: %s", resourceName, fieldName, value));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=value;
	}
}
