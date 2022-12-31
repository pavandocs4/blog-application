package com.example.src.main.bloggingapp.exception;

public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3562327697141287497L;
	String resourceName;
	String fieldName;
	Object fieldValue;
	
	public ResourceNotFoundException(String resourceName, String fieldName, Object value) {
		super(String.format("%s not found with %s: %s", resourceName, fieldName, value));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=value;
	}
	

}
