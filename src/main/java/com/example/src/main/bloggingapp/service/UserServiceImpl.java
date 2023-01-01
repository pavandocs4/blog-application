package com.example.src.main.bloggingapp.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.src.main.bloggingapp.dto.UserDTO;
import com.example.src.main.bloggingapp.dtoconverter.UserDTOConverter;
import com.example.src.main.bloggingapp.entity.User;
import com.example.src.main.bloggingapp.exception.ResourceAlreadyPresentException;
import com.example.src.main.bloggingapp.exception.ResourceNotFoundException;
import com.example.src.main.bloggingapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserDTOConverter dtoConverter;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDTO addUser(UserDTO userdto) {
		User user=new User();
		if(!userExists(userdto.getUsername())) {
			 user= dtoConverter.objFromDTO(userdto);
			userRepo.save(user);
		}
		else { 
			throw new ResourceAlreadyPresentException("User", "username", userdto.getUsername());
		}
		
		return dtoConverter.objToDTO(user);
	}

	@Override
	public UserDTO updateUser(UserDTO userdto) {
		String username= userdto.getUsername();
		User userToUpdate=Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(
				()-> new ResourceNotFoundException("User", "username", username));
		userToUpdate.setUsername(Optional.ofNullable(userdto.getUsername()).orElse(userToUpdate.getUsername()));
		userToUpdate.setPassword(Optional.ofNullable(userdto.getPassword()).orElse(userToUpdate.getPassword()));
		userToUpdate.setEmail(Optional.ofNullable(userdto.getEmail()).orElse(userToUpdate.getEmail()));
		userToUpdate.setAbout(Optional.ofNullable(userdto.getAbout()).orElse(userToUpdate.getAbout()));
		userToUpdate.setAuditInsertDate(Optional.ofNullable(userToUpdate.getAuditInsertDate()).orElse(new Date()))
		.setAuditUpdateDate(new Date())
		.setUserAccess(Optional.ofNullable(userdto.getUserAccess()).orElse(userToUpdate.getUserAccess()));
		userRepo.save(userToUpdate);
		return dtoConverter.objToDTO(userToUpdate);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> userList=userRepo.findAll();
		List<UserDTO> list= userList.stream().map(u-> dtoConverter.objToDTO(u)).collect(Collectors.toList());
		return list;
	}

	@Override
	public UserDTO getUser(int id) {
		User user= userRepo.findById(id).orElseThrow((()->new ResourceNotFoundException("User", "Id", id)));
		return dtoConverter.objToDTO(user);
	}

	@Override
	public String deleteUser(int id) {
		User user= userRepo.findById(id).orElseThrow((()->new ResourceNotFoundException("User", "Id", id)));
		userRepo.delete(user);
		return "User deleted successfully";		
	}
	
	private boolean userExists(String username) {
		try {
			User user= userRepo.findByUsername(username);
			return Objects.nonNull(user);
		}catch(ResourceNotFoundException e) {
			throw new ResourceNotFoundException("User", "username", username);
		}
	}


}
