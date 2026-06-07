package com.iekakmh.bookandauthor.domainLayer.models.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iekakmh.bookandauthor.domainLayer.models.entities.Book;

public interface BookRepository extends JpaRepository<Book, String>{
	
		// findAll()    -> inherited from JpaRepository (returns List<City>)
		// findById(id) -> inherited from JpaRepository (returns Optional<City>)
		// save(entity) -> inherited from JpaRepository (insert or update)
		// deleteById() -> inherited from JpaRepository
	
		List<Book> findByIsbn(String Isbn);

}
