package com.example.src.main.bloggingapp.dtoconverter;

import com.example.src.main.bloggingapp.dto.UserDTO;
import com.example.src.main.bloggingapp.entity.User;

public interface DTOConverter<T, U> {
	U objToDTO(T t);
	T objFromDTO(U u);
}
