package com.example.src.main.bloggingapp.entity;

import java.util.List;

import com.example.src.main.bloggingapp.dto.CommentDTO;

public class CommentPageRequest {
	
	private List<CommentDTO> contents;
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalRecords;
	private boolean isLastPage;
	private Integer totalPages;
	
	public CommentPageRequest() {
		
	}

	public List<CommentDTO> getContents() {
		return contents;
	}

	public CommentPageRequest setContents(List<CommentDTO> contents) {
		this.contents = contents;
		return this;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public CommentPageRequest setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public CommentPageRequest setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public CommentPageRequest setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
		return this;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public CommentPageRequest setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
		return this;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public CommentPageRequest setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
		return this;
	}

	@Override
	public String toString() {
		return "CommentPageRequest [contents=" + contents + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalRecords=" + totalRecords + ", isLastPage=" + isLastPage + ", totalPages=" + totalPages + "]";
	}
	

}
