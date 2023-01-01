package com.example.src.main.bloggingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.src.main.bloggingapp.dto.CategoryDTO;

@Service
public interface CategoryService {
	public CategoryDTO addNewCategory(CategoryDTO dto);
	public CategoryDTO updateCategory(CategoryDTO dto);
	public CategoryDTO getCategoryById(Integer id);
	public List<CategoryDTO> getAllCategories();
	public String deleteCategory(Integer id);
}
