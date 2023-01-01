package com.example.src.main.bloggingapp.dto;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class UserDTO {
	private int id;
	
	@NotEmpty
	@Size(min=4, message="username must be at least of 4 characters")
	private String username;
	
	@NotNull
	@Size(min=8, max=12, message="password must be in range of 8 to 10 characters")
	private String password;
	private String email;
	private String about;
	private Date auditInsertDate;
	private Date auditUpdateDate;
	private String userAccess;
	
	public int getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getAbout() {
		return about;
	}
	
	public Date getAuditInsertDate() {
		return auditInsertDate;
	}
	public Date getAuditUpdateDate() {
		return auditUpdateDate;
	}
	
	public String getUserAccess() {
		return userAccess;
	}
	public UserDTO setUserAccess(String userAccess) {
		this.userAccess = userAccess;
		return this;
	}
	public UserDTO setAuditInsertDate(Date auditInsertDate) {
		this.auditInsertDate = auditInsertDate;
		return this;
	}
	public UserDTO setAuditUpdateDate(Date auditUpdateDate) {
		this.auditUpdateDate = auditUpdateDate;
		return this;
	}
	public UserDTO setId(int id) {
		this.id = id;
		return this;
	}
	public UserDTO setUsername(String username) {
		this.username = username;
		return this;
	}
	public UserDTO setPassword(String password) {
		this.password = password;
		return this;
	}
	public UserDTO setEmail(String email) {
		this.email = email;
		return this;
	}
	public UserDTO setAbout(String about) {
		this.about = about;
		return this;
	}
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", about=" + about + "]";
	}
	
	
}


