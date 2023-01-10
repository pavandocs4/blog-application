package com.example.src.main.bloggingapp.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.example.src.main.bloggingapp.dto.CommentDTO;
import com.example.src.main.bloggingapp.entity.Comment;

@Component
public class CommentDTOConverter implements DTOConverter<Comment, CommentDTO>{

	ModelMapper mapper= new ModelMapper();
	@Override
	public CommentDTO objToDTO(Comment t) {
		return mapper.map(t, CommentDTO.class);
	}

	@Override
	public Comment objFromDTO(CommentDTO u) {
		return mapper.map(u, Comment.class);
	}

}
