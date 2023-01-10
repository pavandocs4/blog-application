package com.example.src.main.bloggingapp.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;


public class CommentDTO {
	private Integer id;

	
	//These validations are applicable only if we are taking the CommentDto object in request
	@NotEmpty
	@Size(min=10)
	private String content;
	
	private Integer postId;
	
	private Date auditInsertDate;
	
	private Date auditUpdateDate;
	
	public CommentDTO(){
		
	}
	
	public CommentDTO(Integer id, @NotEmpty @Size(min = 10) String content, Integer postId, Date auditInsertDate,
			Date auditUpdateDate) {
		super();
		this.id = id;
		this.content = content;
		this.postId = postId;
		this.auditInsertDate = auditInsertDate;
		this.auditUpdateDate = auditUpdateDate;
	}



	public Integer getId() {
		return id;
	}

	public CommentDTO setId(Integer id) {
		this.id = id;
		return this;
	}

	public String getContent() {
		return content;
	}

	public CommentDTO setContent(String content) {
		this.content = content;
		return this;
	}

	public Integer getpostId() {
		return postId;
	}

	public CommentDTO setpostId(Integer postId) {
		this.postId = postId;
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
		return "CommentDTO [id=" + id + ", content=" + content + ", postId=" + postId + ", auditInsertDate="
				+ auditInsertDate + ", auditUpdateDate=" + auditUpdateDate + "]";
	}
	

}
