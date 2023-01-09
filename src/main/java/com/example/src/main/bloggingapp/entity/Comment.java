package com.example.src.main.bloggingapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="commnent_id")
	private Integer id;
	
	private String content;
	
	@ManyToOne
	@JoinColumn(name="post_id")
	private Post post;
	
}
