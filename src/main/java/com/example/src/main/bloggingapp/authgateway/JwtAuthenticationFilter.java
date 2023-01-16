package com.example.src.main.bloggingapp.authgateway;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.src.main.bloggingapp.serviceImpl.UserDetailsServiceImpl;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	JwtTockenHelper helper;

	@Autowired
	UserDetailsServiceImpl userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String username = null;

		String authToken = request.getHeader("Authorization");
		if (!StringUtils.isEmpty(authToken) && authToken.startsWith("Bearer")) {
			String token = authToken.substring(7);
			try {
				username = this.helper.getUsernameFromToken(token);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Invalid Token");
		}

		// validate token
		if (Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails user = userService.loadUserByUsername(username);
			if (this.helper.validateToken(authToken.substring(7), user)) {
				UsernamePasswordAuthenticationToken usernamePassAuthenticationToken = new UsernamePasswordAuthenticationToken(
						user, null, user.getAuthorities());
				usernamePassAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePassAuthenticationToken);
			}
			else {
				System.out.println("Invalid JWT token");
			}
		}
		else {
			System.out.println("Username or context is null");
		}
		filterChain.doFilter(request, response);
	}

}
