package com.example.src.main.bloggingapp.exception;


public class InvalidCredentialsException extends RuntimeException{
	String username;
	String password;
	public InvalidCredentialsException(String username, String password) {
	  super(String.format("Unable to login with %s and %s",username,password));
	  this.username=username;
	  this.password=password;
	}
}
