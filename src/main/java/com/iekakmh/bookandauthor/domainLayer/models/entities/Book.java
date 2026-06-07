package com.iekakmh.bookandauthor.domainLayer.models.entities;

import java.util.ArrayList;
import java.util.List;

import com.iekakmh.bookandauthor.domainLayer.models.enums.*;

import jakarta.persistence.*;

@Entity(name = DatabaseTables.BOOK)
public class Book {

	@Id
	private String isbn;
	
	private String title;
	private String category;
	private int year;
	
	@ManyToMany
	@JoinTable(
			name = "book_author",
			joinColumns = @JoinColumn(name = "book_isbn"),
			inverseJoinColumns = @JoinColumn(name = "author_id")
	)
	private List<Author> authors = new ArrayList<>();
	
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
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}
