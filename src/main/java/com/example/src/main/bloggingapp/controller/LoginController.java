package com.example.src.main.bloggingapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.src.main.bloggingapp.common.objects.JwtAuthResponse;
import com.example.src.main.bloggingapp.exception.InvalidCredentialsException;
import com.example.src.main.bloggingapp.authgateway.JwtTockenHelper;
import com.example.src.main.bloggingapp.common.objects.JwtAuthRequest;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	JwtTockenHelper tokenHelper;
	
	@Autowired
    AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> getToken(@RequestBody JwtAuthRequest token) throws Exception{
		JwtAuthResponse res= new JwtAuthResponse();
	    authenticate(token.getUsername(), token.getPassword());
	    
	    UserDetails userDetails= userService.loadUserByUsername(token.getUsername());
	    
	    String resToken= tokenHelper.generateToken(userDetails);
	    
	    res.setToken(resToken);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		UsernamePasswordAuthenticationToken authToken= new UsernamePasswordAuthenticationToken(username, password);
		try {
			authenticationManager.authenticate(authToken);
		}catch (BadCredentialsException e) {
			throw new InvalidCredentialsException(username, password);
		}
	
	}
	
}
