package com.example.src.main.bloggingapp.dtoconverter;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.src.main.bloggingapp.dto.UserDTO;
import com.example.src.main.bloggingapp.entity.User;

@Component
public class UserDTOConverter implements DTOConverter<User, UserDTO> {

	@Override
	public UserDTO objToDTO(User user) {
		UserDTO dto =new UserDTO();
		dto.setId(user.getId())
			.setUsername(user.getUsername())
			.setPassword(user.getPassword())
			.setEmail(user.getEmail())
			.setAbout(user.getAbout())
			.setAuditInsertDate(Optional.ofNullable(user.getAuditInsertDate()).orElse(new Date()))
			.setAuditUpdateDate(Optional.ofNullable(user.getAuditUpdateDate()).orElse(new Date()))
			.setUserAccess(user.getUserAccess());

			return dto;
	}

	@Override
	public User objFromDTO(UserDTO dto) {
		User user=new User();
		user.setUsername(dto.getUsername())
			.setPassword(dto.getPassword())
			.setEmail(dto.getEmail())
			.setAbout(dto.getAbout())
			.setAuditInsertDate(Optional.ofNullable(dto.getAuditInsertDate()).orElse(new Date()))
			.setAuditUpdateDate(Optional.ofNullable(dto.getAuditInsertDate()).orElse(new Date()))
			.setUserAccess(dto.getUserAccess());
		if(Objects.nonNull(dto.getId())) {
			user.setId(dto.getId());
		}
		return user;
			
	}

}
