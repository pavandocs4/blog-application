package com.example.src.main.bloggingapp.controller;

import java.util.HashMap;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.src.main.bloggingapp.dto.PostDTO;
import com.example.src.main.bloggingapp.entity.PostsPageRequest;
import com.example.src.main.bloggingapp.service.PostService;

@RestController
public class PostsController {
	@Autowired
	private PostService service;
	
	@PostMapping("/addPost")
	public ResponseEntity<PostDTO> addNewPost(@Valid @RequestBody HashMap<String, Object> input){
		PostDTO res= service.addNewPost(input);
		return new ResponseEntity<PostDTO>(res, HttpStatus.OK);
	}
	
	@PutMapping("/updatePost")
	public ResponseEntity<PostDTO> UpdatePost(@Valid @RequestBody PostDTO input){
		PostDTO res= service.updatePost(input);
		return new ResponseEntity<PostDTO>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostById/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Integer id){
		PostDTO res= service.getPostById(id);
		return new ResponseEntity<PostDTO>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostByCategory/{category}")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("category") String category){
		List<PostDTO> res= service.getPostsByCategory(category);
		return new ResponseEntity<List<PostDTO>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostsByUser/{username}")
	public ResponseEntity<List<PostDTO>> getPostByUsername(@PathVariable("username") String username){
		List<PostDTO> res= service.getPostsByUsername(username);
		return new ResponseEntity<List<PostDTO>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostsByUsernameAndCategory/{username}/{category}")
	public ResponseEntity<List<PostDTO>> getPostByUsername(
			@PathVariable("username") String username,
			@PathVariable("category") String category){
		List<PostDTO> res= service.getPostsByUsernameAndCategory(username, category);
		return new ResponseEntity<List<PostDTO>>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<PostsPageRequest> getAllPosts(
			@RequestParam(value= "pageNo", defaultValue="0", required=false) Integer pageNo,
			@RequestParam(value= "pageSize", defaultValue="2", required= false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue="id", required=false) String sortParameter,
			@RequestParam(value="order", defaultValue="asc", required= false) String order
			){
		PostsPageRequest res= service.getAllPosts(pageNo, pageSize, sortParameter, order);
		return new ResponseEntity<PostsPageRequest>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMapping/{id}")
	public ResponseEntity<String> deleteMapping(@PathVariable("id") Integer id){
		String res= service.deletePost(id);
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	
}
