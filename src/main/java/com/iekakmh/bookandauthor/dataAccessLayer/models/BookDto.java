package com.iekakmh.bookandauthor.dataAccessLayer.models;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class BookDto {
	
	@NotBlank(message = "ISBN is required.")
	private String isbn;
	
	@NotBlank(message = "Title is required.")
	private String title;
	
	private String category;
	private int year;
	
	private List<Integer> authorsIds;
	
	//setters and getters
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public List<Integer> getAuthorsIds() {
		return authorsIds;
	}
	public void setAuthorsIds(List<Integer> authorsIds) {
		this.authorsIds = authorsIds;
	}
}
