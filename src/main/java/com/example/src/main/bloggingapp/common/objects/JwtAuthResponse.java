package com.example.src.main.bloggingapp.common.objects;

import org.springframework.stereotype.Component;

@Component
public class JwtAuthResponse {
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
