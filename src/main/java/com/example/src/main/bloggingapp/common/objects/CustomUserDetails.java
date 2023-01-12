package com.example.src.main.bloggingapp.common.objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.src.main.bloggingapp.entity.User;
import com.example.src.main.bloggingapp.repository.UserRepository;

@Component
public class CustomUserDetails implements UserDetails{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	private User user;
	
	public CustomUserDetails() {
	}
	
	public CustomUserDetails(User user) {
		this.user=user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
				List<GrantedAuthority> auth=new ArrayList<>();
				auth.add(new SimpleGrantedAuthority(user.getUserAccess()));
				return auth;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
