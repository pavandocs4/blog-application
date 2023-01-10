package com.example.src.main.bloggingapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.src.main.bloggingapp.dto.CommentDTO;
import com.example.src.main.bloggingapp.entity.CommentPageRequest;
import com.example.src.main.bloggingapp.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	
	
	@PostMapping("/add")
	public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody HashMap<String, Object> input){

		CommentDTO res = commentService.addNewComment(input);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<CommentDTO> addComment(@Valid @RequestBody CommentDTO dto){

		CommentDTO res = commentService.updateComment(dto);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getById/{id}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable("id") Integer id){
		CommentDTO res= commentService.getCommentById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);	
	}
	
	@GetMapping("/getByPostId/{id}")
	public ResponseEntity<CommentPageRequest> getCommentByPostId(@PathVariable("id") Integer id){
		CommentPageRequest res= commentService.getCommentByPostId(id);
		return new ResponseEntity<>(res, HttpStatus.OK);	
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteCommentById(@PathVariable("id") Integer id){
		String res= commentService.deleteById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);	
	}
	
	@DeleteMapping("/deleteByPostId/{id}")
	public ResponseEntity<String> deleteCommentByPostId(@PathVariable("id") Integer id){
		String res= commentService.deleteByPostId(id);
		return new ResponseEntity<>(res, HttpStatus.OK);	
	}
}
