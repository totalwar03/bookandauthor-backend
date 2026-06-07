package com.iekakmh.bookandauthor.domainLayer.models.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.iekakmh.bookandauthor.domainLayer.models.enums.DatabaseTables;

import jakarta.persistence.*;

@Entity(name = DatabaseTables.AUTHOR)
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String nationality;
	private LocalDate birthdate;
	
	@ManyToMany (mappedBy = "authors")
	private List<Book> books = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
