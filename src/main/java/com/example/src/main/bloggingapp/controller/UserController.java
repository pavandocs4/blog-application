package com.example.src.main.bloggingapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userdto) {
		UserDTO dto= userService.addUser(userdto);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	@PutMapping(path="/updateUser")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userdto) {
		UserDTO dto= userService.updateUser(userdto);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping(path="/getUser/{id}")
	public UserDTO getUserById(@PathVariable("id") int id) {
		return userService.getUser(id);
	}
	
	@GetMapping(path="/getAllUsers")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@DeleteMapping(path="/deleteUser/{id}")
	public String addUser(@PathVariable("id") int id) {
		return userService.deleteUser(id);
	}
	
}
