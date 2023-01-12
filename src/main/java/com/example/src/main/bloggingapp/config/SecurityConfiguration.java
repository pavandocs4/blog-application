package com.example.src.main.bloggingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.src.main.bloggingapp.serviceImpl.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsServiceImpl userDetailsService;
	
	
	  @Override protected void configure(HttpSecurity http) throws Exception {
	  
	  http .csrf() .disable() .authorizeHttpRequests() .anyRequest()
	  .authenticated() .and() .httpBasic();
	  
	  }
	 
	
	protected void configure(AuthenticationManagerBuilder http) throws Exception {
		http.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}
	
	protected PasswordEncoder getPasswordEncoder() {
		
		return  NoOpPasswordEncoder.getInstance();  // BCryptPasswordEncoder();
		
	}
}
