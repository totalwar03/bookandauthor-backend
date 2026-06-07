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
public class AuthorService {
	
	@Autowired
	private AuthorRepository authorRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private Validator validator;
	
	public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository, Validator validator) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.validator = validator;
	}
	public List<AuthorDto> getAuthors() {
		
		return authorRepository.findAll()
				.stream()
				.map(this::toDTO)
				.toList();
	}
	public AuthorDto getAuthorById(int id) {
		Author author =  authorRepository.findById(id)
			.orElseThrow( () -> new RuntimeException("Author not found."));
		return toDTO(author);
	}
	
	public AuthorDto createAuthor(AuthorDto dto) throws Exception {
		
		List<String> validationViolationMessages = validator.validate(dto).stream()
	            .map(d -> d.getMessage()).collect(Collectors.toList());
	    if (validationViolationMessages.size() > 0) {
	        throw new Exception(String.join("\n", validationViolationMessages));
	    }

	    Author author = new Author();
	    author.setName(dto.getName());
	    author.setNationality(dto.getNationality());
	    author.setBirthdate(dto.getBirthdate());

	    // Αποθήκευσε πρώτα τον author χωρίς βιβλία
	    Author savedAuthor = authorRepository.save(author);

	    // Μετά ενημέρωσε τα βιβλία
	    if (dto.getBookIsbn() != null && !dto.getBookIsbn().isEmpty()) {
	        List<Book> books = bookRepository.findAllById(dto.getBookIsbn());

	        if (books.size() != dto.getBookIsbn().size()) {
	            throw new Exception("Ένα ή περισσότερα βιβλία δεν βρέθηκαν.");
	        }

	        for (Book book : books) {
	            book.getAuthors().add(savedAuthor);
	            bookRepository.save(book);
	        }
	        savedAuthor.setBooks(books);
	    }

	    return toDTO(authorRepository.save(savedAuthor));
	}
	
	public AuthorDto updateAuthor(int id, AuthorDto dto) throws Exception {
		
		List<String> validationViolationMessages = validator.validate(dto).stream().map(d -> d.getMessage()).collect(Collectors.toList());
		if (validationViolationMessages.size() > 0) {
			throw new Exception(String.join("\n",validationViolationMessages));
		}
		Author author = authorRepository.findById(id).orElseThrow( () -> new Exception("Author was not found."));
		
		author.setName(dto.getName());
		author.setNationality(dto.getNationality());
		author.setBirthdate(dto.getBirthdate());
		
		if (dto.getBookIsbn() != null) {
			List<Book> books = bookRepository.findAllById(dto.getBookIsbn());
			author.setBooks(books);
			
			for (Book book : books) {
		        book.getAuthors().add(author);
		        bookRepository.save(book);
		    }
		}
		return toDTO(authorRepository.save(author));
	}
	
	public void deleteAuthor(int id) throws Exception {
		Author author = authorRepository.findById(id)
	            .orElseThrow(() -> new Exception("Author was not found"));

	    // Καθάρισε τις σχέσεις με τα βιβλία πριν διαγράψεις
	    for (Book book : author.getBooks()) {
	        book.getAuthors().remove(author);
	        bookRepository.save(book);
	    }
	    author.getBooks().clear();

	    authorRepository.delete(author);
		
	}
	private AuthorDto toDTO(Author author) {
		
		AuthorDto dto= new AuthorDto();
		dto.setId(author.getId());
		dto.setBirthdate(author.getBirthdate());
		dto.setName(author.getName());
		dto.setNationality(author.getNationality());
		dto.setBookIsbn(
				author.getBooks().stream().map(Book::getIsbn).toList()
		);
		return dto;
	}	
}
