package com.example.src.main.bloggingapp.dtoconverter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.src.main.bloggingapp.dto.CategoryDTO;
import com.example.src.main.bloggingapp.entity.Category;

@Component
public class CategoryDTOConverter implements DTOConverter<Category, CategoryDTO>{
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDTO objToDTO(Category t) {
		return mapper.map(t, CategoryDTO.class);
	}

	@Override
	public Category objFromDTO(CategoryDTO u) {
		return mapper.map(u, Category.class);
	}

	}
