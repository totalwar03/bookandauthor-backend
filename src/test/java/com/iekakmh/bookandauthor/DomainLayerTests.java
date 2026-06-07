package com.iekakmh.bookandauthor;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iekakmh.bookandauthor.domainLayer.models.entities.Book;
import com.iekakmh.bookandauthor.domainLayer.models.repositories.*;

@SpringBootTest
public class DomainLayerTests {
		
	@Autowired
	private BookRepository bookRepository;
//	@Autowired
//	private AuthorRepository authorRepository;
	
	@Test
	void findBookByTitle() {
		
		List<Book> books = bookRepository.findAll();
		
		Assertions.assertThat(books).isNotNull();
		Assertions.assertThat(books.size()).isGreaterThan(0);
	}
	
	
	

}
