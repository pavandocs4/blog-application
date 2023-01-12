package com.example.src.main.bloggingapp.serviceImpl;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.src.main.bloggingapp.common.objects.CustomUserDetails;
import com.example.src.main.bloggingapp.entity.User;
import com.example.src.main.bloggingapp.exception.ResourceNotFoundException;
import com.example.src.main.bloggingapp.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user =Optional.ofNullable(userRepo.findByUsername(username)).orElseThrow(()->
		new ResourceNotFoundException("User", "username", username));
		
		return new CustomUserDetails(user);
	}

}
