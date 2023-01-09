package com.example.src.main.bloggingapp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.src.main.bloggingapp.dto.PostDTO;
import com.example.src.main.bloggingapp.entity.PostsPageRequest;
import com.example.src.main.bloggingapp.service.FileUploadService;
import com.example.src.main.bloggingapp.service.PostService;
import com.example.src.main.bloggingapp.config.BloggingConstants;

@RestController
public class PostsController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileUploadService uploadService;
	
	@Value("${project.upload.image}")
	String path;
	
	@PostMapping("/addPost")
	public ResponseEntity<PostDTO> addNewPost(@Valid @RequestBody HashMap<String, Object> input){
		PostDTO res= postService.addNewPost(input);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/updatePost")
	public ResponseEntity<PostDTO> UpdatePost(@Valid @RequestBody PostDTO input){
		PostDTO res= postService.updatePost(input);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostById/{id}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Integer id){
		PostDTO res= postService.getPostById(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostByCategory/{category}")
	public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable("category") String category){
		List<PostDTO> res= postService.getPostsByCategory(category);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostsByUser/{username}")
	public ResponseEntity<List<PostDTO>> getPostByUsername(@PathVariable("username") String username){
		List<PostDTO> res= postService.getPostsByUsername(username);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getPostsByUsernameAndCategory/{username}/{category}")
	public ResponseEntity<List<PostDTO>> getPostByUsername(
			@PathVariable("username") String username,
			@PathVariable("category") String category){
		List<PostDTO> res= postService.getPostsByUsernameAndCategory(username, category);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/getAllPosts")
	public ResponseEntity<PostsPageRequest> getAllPosts(
			@RequestParam(value= "pageNo", defaultValue=BloggingConstants.DEFAULT_PAGE_NO, required=false) Integer pageNo,
			@RequestParam(value= "pageSize", defaultValue=BloggingConstants.DEFAULT_PAGE_SIZE, required= false) Integer pageSize,
			@RequestParam(value="sortBy", defaultValue=BloggingConstants.DEFAULT_SORT_ENTITY, required=false) String sortParameter,
			@RequestParam(value="order", defaultValue=BloggingConstants.DEFAULT_SORT_ORDER, required= false) String order
			){
		PostsPageRequest res= postService.getAllPosts(pageNo, pageSize, sortParameter, order);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteMapping/{id}")
	public ResponseEntity<String> deleteMapping(@PathVariable("id") Integer id){
		String res= postService.deletePost(id);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{parameter}")
	public ResponseEntity<List<PostDTO>> searchPost(@PathVariable("parameter") String param){
		List<PostDTO> res= postService.searchPost("%"+param+"%");
		return new ResponseEntity<>(res, HttpStatus.FOUND);
	}

	@PostMapping("/posts/uploadImage/{id}")
	public ResponseEntity<PostDTO> uploadFile(@RequestParam("image") MultipartFile image,
			@PathVariable("id") Integer postId) throws IOException {
		PostDTO res = postService.getPostById(postId);
		String fileName = uploadService.uploadFile(path, image);
		res.setImage(fileName);
		res = postService.updatePost(res);
		return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	@GetMapping(value= "/posts/getImage/{id}", produces= MediaType.IMAGE_PNG_VALUE)
	public void getImageFromPost(@PathVariable("id") Integer postId, HttpServletResponse responce) throws IOException {
		PostDTO res = postService.getPostById(postId);
		String fileName = res.getImage();
		InputStream inputFile= uploadService.getUploadedFile(path, fileName);
		responce.setContentType(MediaType.IMAGE_PNG_VALUE);
		StreamUtils.copy(inputFile, responce.getOutputStream());
	}
}
