package com.example.src.main.bloggingapp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_data")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_id")
	private int id;
	
	@Column(name="user_name", length= 12, nullable=false)
	private String username;
	
	@Column(length=12, nullable=false)
	private String password;
	
	@Column(name="email_id")
	private String email;
	
	@Column(name="access")
	private String userAccess;
	
	@Column(name="auditInsertDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditInsertDate;
	
	@Column(name="auditUpdateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date auditUpdateDate;
		
	private String about;
	
	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL)
	private List<Post> posts=new ArrayList<>();
	
	public User(int id, String username, String password, String email, String userAccess, Date auditInsertDate,
			Date auditUpdateDate, String about) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userAccess = userAccess;
		this.auditInsertDate = auditInsertDate;
		this.auditUpdateDate = auditUpdateDate;
		this.about = about;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

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

	public String getUserAccess() {
		return userAccess;
	}

	public Date getAuditInsertDate() {
		return auditInsertDate;
	}

	public Date getAuditUpdateDate() {
		return auditUpdateDate;
	}

	public User setUserAccess(String userAccess) {
		this.userAccess = userAccess;
		return this;
	}

	public User setAuditInsertDate(Date auditInsertDate) {
		this.auditInsertDate = auditInsertDate;
		return this;
	}

	public User setAuditUpdateDate(Date auditUpdateDate) {
		this.auditUpdateDate = auditUpdateDate;
		return this;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public User setAbout(String about) {
		this.about = about;
		return this;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", about="
				+ about + "]";
	}
	
}
