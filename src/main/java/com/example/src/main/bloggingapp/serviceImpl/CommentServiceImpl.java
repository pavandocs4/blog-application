package com.example.src.main.bloggingapp.serviceImpl;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.src.main.bloggingapp.dto.CommentDTO;
import com.example.src.main.bloggingapp.dto.PostDTO;
import com.example.src.main.bloggingapp.dtoconverter.CommentDTOConverter;
import com.example.src.main.bloggingapp.dtoconverter.PostDTOConverter;
import com.example.src.main.bloggingapp.entity.Comment;
import com.example.src.main.bloggingapp.entity.CommentPageRequest;
import com.example.src.main.bloggingapp.exception.ResourceNotFoundException;
import com.example.src.main.bloggingapp.repository.CommentRepository;
import com.example.src.main.bloggingapp.service.CommentService;
import com.example.src.main.bloggingapp.service.PostService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.src.main.bloggingapp.config.BloggingConstants;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDTOConverter converter;

	@Autowired
	PostDTOConverter postDTOConverter;

	@Autowired
	CommentRepository commentRepo;

	@Autowired
	PostService postService;

	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	@Override
	public CommentDTO addNewComment(HashMap<String, Object> input) {

		PostDTO post = postService
				.getPostById(OBJECT_MAPPER.convertValue(input.get(BloggingConstants.POST_ID), Integer.class));

		CommentDTO dto = OBJECT_MAPPER.convertValue(input.get("comment"), CommentDTO.class);
		dto.setpostId(post.getId());
		dto.setAuditInsertDate(new Date());
		Comment comment = converter.objFromDTO(dto);
		commentRepo.save(comment);
		return converter.objToDTO(comment);
	}

	@Override
	public CommentDTO updateComment(@Valid CommentDTO dto) {
		Comment comment = commentRepo.findById(dto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", dto.getId()));
		comment.setContent(dto.getContent());
		comment.setAuditUpdateDate(new Date());
		commentRepo.save(comment);
		return converter.objToDTO(comment);
	}

	@Override
	public CommentDTO getCommentById(Integer id) {
		Comment comment = commentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", id));
		return converter.objToDTO(comment);
	}

	@Override
	public CommentPageRequest getCommentByPostId(Integer id) {
		Integer pageNo = 0;
		Integer pageSize = 5;
		// Pageable p= PageRequest.
		List<Comment> comments = commentRepo.findAll();
		List<CommentDTO> commentList = comments.stream().map(c -> converter.objToDTO(c)).collect(Collectors.toList());
		List<CommentDTO> response = commentList.stream().filter(com -> com.getpostId().equals(id))
				.sorted((a, b) -> a.getId() - b.getId()).collect(Collectors.toList());
		List<CommentDTO> finalList = response.stream().limit(Long.parseLong(pageSize.toString()))
				.collect(Collectors.toList());
		boolean lastPage = response.size() <= pageSize;
		Integer size = response.size() <= pageSize ? response.size() : pageSize;
		Integer totalPages = response.size() / pageSize;
		CommentPageRequest res = new CommentPageRequest();
		res.setContents(finalList).setLastPage(lastPage).setPageNo(pageNo).setPageSize(size).setTotalPages(totalPages)
				.setTotalRecords(response.size());
		return res;

	}

	@Override
	public String deleteById(Integer id) {
		Comment comment = commentRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", id));
		commentRepo.deleteById(comment.getId());
		return "Record Deleted SuccessFully";
	}

	@Override
	public String deleteByPostId(Integer id) {
		
		List<Comment> comments = commentRepo.findAll();
		List<Comment> response = comments.stream().filter(com -> com.getPost().getId().equals(id)).collect(Collectors.toList());
		response.stream().map(x-> x.getId()).forEach(i-> commentRepo.deleteById(i));
		return "Record Deleted SuccessFully";
	}

}
