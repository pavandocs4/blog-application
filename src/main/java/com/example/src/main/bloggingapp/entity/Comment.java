package com.example.src.main.bloggingapp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "commnent_id")
	private Integer id;

	@NotEmpty
	@Size(min = 10)
	private String content;

	@ManyToOne
	@JsonIgnore // This is very important property else there will be a error for jackson
				// binding
	@JoinColumn(name = "post_id")
	private Post post;

	@Temporal(TemporalType.TIMESTAMP)
	private Date auditInsertDate;

	@Temporal(TemporalType.TIMESTAMP)
	private Date auditUpdateDate;

	public Integer getId() {
		return id;
	}

	public Comment setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getContent() {
		return content;
	}

	public Comment setContent(String content) {
		this.content = content;
		return this;
	}

	public Post getPost() {
		return post;
	}

	public Comment setPost(Post post) {
		this.post = post;
		return this;
	}

	public Date getAuditInsertDate() {
		return auditInsertDate;
	}

	public void setAuditInsertDate(Date auditInsertDate) {
		this.auditInsertDate = auditInsertDate;
	}

	public Date getAuditUpdateDate() {
		return auditUpdateDate;
	}

	public void setAuditUpdateDate(Date auditUpdateDate) {
		this.auditUpdateDate = auditUpdateDate;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", content=" + content + ", post=" + post + ", auditInsertDate=" + auditInsertDate
				+ ", auditUpdateDate=" + auditUpdateDate + "]";
	}

}
