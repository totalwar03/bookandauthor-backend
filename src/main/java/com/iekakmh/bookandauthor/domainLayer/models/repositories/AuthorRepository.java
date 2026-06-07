package com.iekakmh.bookandauthor.domainLayer.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iekakmh.bookandauthor.domainLayer.models.entities.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	
	List<Author> findByName(String name);
}
