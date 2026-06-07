package com.iekakmh.bookandauthor.dataAccessLayer.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.iekakmh.bookandauthor.dataAccessLayer.models.*;
import com.iekakmh.bookandauthor.domainLayer.models.entities.*;
import com.iekakmh.bookandauthor.domainLayer.models.repositories.*;

import jakarta.validation.Validator;

@Service
@Transactional
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private Validator validator;
	
	public BookService(BookRepository bookRepository, AuthorRepository authorRepository, Validator validator) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
		this.validator = validator;
	}
	public List<BookDto> getBooks() {
		
		return bookRepository.findAll()
				.stream()
				.map(this::toDTO)
				.toList();
	}
	public BookDto getBookByIsbn(String isbn) {
		
		Book book = bookRepository.findById(isbn)
				.orElseThrow( () -> new RuntimeException("Book was not found"));
		return toDTO(book);
	}
	
	public BookDto createBook(BookDto dto) throws Exception{
		
		List<String> validationViolationMessages = validator.validate(dto).stream().map(d -> d.getMessage()).collect(Collectors.toList());
		if (validationViolationMessages.size() > 0) {
			throw new Exception(String.join("\n",validationViolationMessages));
		}
		
		Book book = new Book();
		
		book.setIsbn(dto.getIsbn());
		book.setTitle(dto.getTitle());
		book.setCategory(dto.getCategory());
		book.setYear(dto.getYear());
		
//		if (dto.getIsbn() == null) {
//			throw new Exception("Isbn is required.");
//		}
		
		if (dto.getAuthorsIds() != null && !dto.getAuthorsIds().isEmpty()) {
			
			List<Author> author = authorRepository.findAllById(dto.getAuthorsIds());
			
			if (author.size() != dto.getAuthorsIds().size()) {
		        throw new Exception("Author not found.");
		    }
			book.setAuthors(author);
		}
		
		return toDTO(bookRepository.save(book));
	}
	public BookDto updateBook(String isbn, BookDto dto) throws Exception {
		
		List<String> validationViolationMessages = validator.validate(dto).stream().map(d -> d.getMessage()).collect(Collectors.toList());
		if (validationViolationMessages.size() > 0) {
			throw new Exception(String.join("\n",validationViolationMessages));
		}
		
		Book book = bookRepository.findById(isbn).orElseThrow( () -> new RuntimeException("Book was not found."));
		
		book.setTitle(dto.getTitle());
		book.setCategory(dto.getCategory());
		book.setYear(dto.getYear());
		
		if(dto.getAuthorsIds() != null) {
			List<Author> authors = authorRepository.findAllById(dto.getAuthorsIds());
			book.setAuthors(authors);
		}
		
		return toDTO(bookRepository.save(book));
	}
	public void deleteBook(String isbn) throws Exception {
		
		if (!bookRepository.existsById(isbn)) {
			throw new Exception("Book was not found.");
		}
		bookRepository.deleteById(isbn);
	}
	private BookDto toDTO(Book book) {
		
		BookDto dto = new BookDto();
		dto.setIsbn(book.getIsbn());
		dto.setTitle(book.getTitle());
		dto.setCategory(book.getCategory());
		dto.setYear(book.getYear());
		dto.setAuthorsIds(
				book.getAuthors().stream().map(Author::getId).toList()
		);
		return dto;
	}
}
