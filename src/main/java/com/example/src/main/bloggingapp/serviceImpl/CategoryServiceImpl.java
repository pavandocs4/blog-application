package com.example.src.main.bloggingapp.serviceImpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.src.main.bloggingapp.dto.CategoryDTO;
import com.example.src.main.bloggingapp.dtoconverter.CategoryDTOConverter;
import com.example.src.main.bloggingapp.entity.Category;
import com.example.src.main.bloggingapp.entity.User;
import com.example.src.main.bloggingapp.exception.ResourceAlreadyPresentException;
import com.example.src.main.bloggingapp.exception.ResourceNotFoundException;
import com.example.src.main.bloggingapp.repository.CategoryRepository;
import com.example.src.main.bloggingapp.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository repo;

	@Autowired
	CategoryDTOConverter dtoConverter;

	@Override
	public CategoryDTO addNewCategory(CategoryDTO dto) {
		if (!isCategoryExists(dto.getTitle())) {
			Category cat = repo.save(dtoConverter.objFromDTO(dto));
			return dtoConverter.objToDTO(cat);
		} else {
			throw new ResourceAlreadyPresentException("Category", "title", dto.getTitle());
		}

	}

	@Override
	public CategoryDTO updateCategory(CategoryDTO dto) {
		Optional<Category> cat = Optional.ofNullable(repo.getById(dto.getId()));
		if(Objects.isNull(cat.get())) {
			throw new ResourceNotFoundException("Category", "id", dto.getId());
		}
				//.orElseThrow(() -> new ResourceNotFoundException("Category", "id", dto.getId());
		cat.get().setTitle(dto.getTitle()).setDescription(dto.getDescription());
		Category categoryToUpdate = repo.save(cat.get());
		return dtoConverter.objToDTO(categoryToUpdate);

	}
	@Override
	public CategoryDTO getCategoryById(Integer id) {
		Category cat= repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		return dtoConverter.objToDTO(cat);
	}

	@Override
	public List<CategoryDTO> getAllCategories() {
		List<Category> cat= repo.findAll();
		List<CategoryDTO> dtoList=  cat.stream().map(c-> dtoConverter.objToDTO(c)).collect(Collectors.toList());	
		return dtoList;
	}

	@Override
	public String deleteCategory(Integer id) {
		Category cat= repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category", "id", id));
		repo.deleteById(id);
		return "Category with id "+id+" deleted successfully";
	}

	public boolean isCategoryExists(String title) {
		Category cat= repo.findByTitle(title).orElseThrow(()->new ResourceNotFoundException("Category", "title", title));
		boolean flag = Objects.nonNull(cat) ? true : false;
		return flag;
	}
}
