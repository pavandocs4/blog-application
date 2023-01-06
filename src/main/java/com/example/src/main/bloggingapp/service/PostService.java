package com.example.src.main.bloggingapp.service;

import java.util.HashMap;
import java.util.List;

import com.example.src.main.bloggingapp.dto.PostDTO;
import com.example.src.main.bloggingapp.entity.PostsPageRequest;

public interface PostService {
	PostDTO addNewPost(HashMap<String, Object> input);
	PostDTO updatePost(PostDTO dto);
	PostDTO getPostById(Integer id);
	List<PostDTO> getPostsByCategory(String category);
	List<PostDTO> getPostsByUsername(String username);
	List<PostDTO> getPostsByUsernameAndCategory(String username, String category);
	PostsPageRequest getAllPosts(Integer pageNo, Integer pageSize, String sortBy, String order);
	String deletePost(Integer id);
}
