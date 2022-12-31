package com.example.src.main.bloggingapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.src.main.bloggingapp.dto.UserDTO;
import com.example.src.main.bloggingapp.service.UserServiceImpl;

@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@RequestMapping(path="/addUser", method= RequestMethod.POST)
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userdto) {
		UserDTO dto= userService.addUser(userdto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@RequestMapping(path="/updateUser", method= RequestMethod.POST)
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userdto) {
		UserDTO dto= userService.updateUser(userdto);
		return ResponseEntity.ok(dto);
	}
	
	@RequestMapping(path="/getUser/{id}", method= RequestMethod.GET)
	public UserDTO getUserById(@PathVariable("id") int id) {
		return userService.getUser(id);
	}
	
	@RequestMapping(path="/getAllUsers", method= RequestMethod.GET)
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@RequestMapping(path="/deleteUser/{id}", method= RequestMethod.DELETE)
	public String addUser(@PathVariable("id") int id) {
		return userService.deleteUser(id);
	}
	
}
