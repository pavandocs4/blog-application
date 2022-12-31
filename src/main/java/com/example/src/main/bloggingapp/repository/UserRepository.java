package com.example.src.main.bloggingapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.src.main.bloggingapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public User findByUsername(String username);

}
