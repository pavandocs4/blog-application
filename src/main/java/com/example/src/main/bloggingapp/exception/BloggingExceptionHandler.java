package com.example.src.main.bloggingapp.exception;

import java.util.HashMap;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BloggingExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		 ApiResponse apiResponse= new ApiResponse(ex.getMessage(), false);
		 return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<String> invalidCredentialsException(InvalidCredentialsException ex){
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ApiResponse> entityNotFoundExceptionHandler(EntityNotFoundException ex){
		 ApiResponse apiResponse= new ApiResponse(ex.getMessage(), false);
		 return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(ResourceAlreadyPresentException.class)
	public ResponseEntity<ApiResponse> resourceFoundExceptionHandler(ResourceAlreadyPresentException ex){
		 ApiResponse apiResponse= new ApiResponse(ex.getMessage(), false);
		 return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
		 Map<String, String> response= new HashMap<>();
		 ex.getAllErrors().forEach(e->{
			 String obj=((FieldError) e).getField();
			 String msg=e.getDefaultMessage();
			 response.put(obj, msg);
		 });
		 return new ResponseEntity<Map<String, String>>(response, HttpStatus.BAD_REQUEST);	
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Map<String, String>> constraintViolationException(ConstraintViolationException ex){
		Map<String, String> res= new HashMap<>();
		ex.getConstraintViolations().stream().forEach(e ->
		{
			String key= e.getConstraintDescriptor().toString();
			String val=e.getMessage();
			res.put(key, val);
		});
		return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
		
	}
}
