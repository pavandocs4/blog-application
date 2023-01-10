package com.example.src.main.bloggingapp.service;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import com.example.src.main.bloggingapp.dto.CommentDTO;
import com.example.src.main.bloggingapp.entity.CommentPageRequest;

public interface CommentService {

	CommentDTO addNewComment(HashMap<String, Object>  input);

	CommentDTO updateComment(@Valid CommentDTO dto);

	CommentDTO getCommentById(Integer id);

	CommentPageRequest getCommentByPostId(Integer id);

	String deleteById(Integer id);

	String deleteByPostId(Integer id);

}
