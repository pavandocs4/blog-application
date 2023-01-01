package com.example.src.main.bloggingapp.repository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.src.main.bloggingapp.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	public Category findByTitle(String title);
	
	/*
	 * @Query("select * from categories c") public List<Category> getAllUsers();
	 */
}
