package com.iekakmh.bookandauthor.dataAccessLayer.models;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class AuthorDto {
	
	private int id;
	
	@NotBlank(message = "Name is a must.")
	private String name;
	private String nationality;
	private LocalDate birthdate;
	private List<String> bookIsbn;
	
	//setters and getters
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
	public List<String> getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(List<String> bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
}
