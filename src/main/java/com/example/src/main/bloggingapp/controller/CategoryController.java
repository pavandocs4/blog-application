package com.example.src.main.bloggingapp.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.src.main.bloggingapp.dto.CategoryDTO;
import com.example.src.main.bloggingapp.dto.UserDTO;
import com.example.src.main.bloggingapp.service.CategoryServiceImpl;

@RestController
public class CategoryController {

	@Autowired
	CategoryServiceImpl service;

	@PostMapping(path = "/addCategory")
	public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO dto) {
		CategoryDTO response = service.addNewCategory(dto);
		return new ResponseEntity<CategoryDTO>(response, HttpStatus.OK);
	}

	@PutMapping(path = "/updateCategory")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO dto) {
		CategoryDTO response = service.updateCategory(dto);
		return ResponseEntity.ok(response);
	}

	@GetMapping(path = "/getCategoryById/{id}")
	public ResponseEntity<CategoryDTO> addCategory(@PathVariable Integer id) {
		CategoryDTO response = service.getCategoryById(id);
		return new ResponseEntity<CategoryDTO>(response, HttpStatus.OK);
	}
	
	@GetMapping(path="/getAllCategories")
	public ResponseEntity<List<CategoryDTO>> getAllCategories(){
		List<CategoryDTO> response= service.getAllCategories();
		return new ResponseEntity<List<CategoryDTO>>(response, HttpStatus.OK);
	}
	
	@DeleteMapping(path="/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") Integer id){
		String response= service.deleteCategory(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}
	
}





