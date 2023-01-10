package com.example.src.main.bloggingapp.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.src.main.bloggingapp.dto.PostDTO;
import com.example.src.main.bloggingapp.entity.Post;

@Component
public class PostDTOConverter implements DTOConverter<Post, PostDTO>{

	@Autowired
	ModelMapper mapper;
	
	@Override
	public PostDTO objToDTO(Post t) {
		return mapper.map(t, PostDTO.class);
	}

	@Override
	public Post objFromDTO(PostDTO u) {
		return mapper.map(u, Post.class);
	}

}
