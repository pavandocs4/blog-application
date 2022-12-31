package com.example.src.main.bloggingapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.src.main.bloggingapp.dto.UserDTO;
import com.example.src.main.bloggingapp.entity.User;

@Service
public interface UserService {
	UserDTO addUser(UserDTO userdto);
	UserDTO updateUser(UserDTO userdto);
	List<UserDTO> getAllUsers();
	UserDTO getUser(int id);
	String deleteUser(int id);
}
