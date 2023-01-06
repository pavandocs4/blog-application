package com.example.src.main.bloggingapp.entity;

import java.util.List;

import com.example.src.main.bloggingapp.dto.PostDTO;

public class PostsPageRequest {
	
	private List<PostDTO> contents;
	private Integer pageNo;
	private Integer pageSize;
	private Integer totalRecords;
	private boolean isLastPage;
	private Integer totalPages;
	
	public PostsPageRequest() {
		
	}

	public List<PostDTO> getContents() {
		return contents;
	}

	public PostsPageRequest setContents(List<PostDTO> contents) {
		this.contents = contents;
		return this;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public PostsPageRequest setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public PostsPageRequest setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getTotalRecords() {
		return totalRecords;
	}

	public PostsPageRequest setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
		return this;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public PostsPageRequest setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
		return this;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public PostsPageRequest setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
		return this;
	}

	@Override
	public String toString() {
		return "PostsPageRequest [contents=" + contents + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", totalRecords=" + totalRecords + ", isLastPage=" + isLastPage + ", totalPages=" + totalPages + "]";
	}
	

}
