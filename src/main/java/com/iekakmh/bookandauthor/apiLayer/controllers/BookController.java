package com.iekakmh.bookandauthor.apiLayer.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.iekakmh.bookandauthor.dataAccessLayer.models.*;
import com.iekakmh.bookandauthor.dataAccessLayer.services.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Book management.")
public class BookController {
	
	private final BookService bookService;

	public BookController(BookService bookService) {
		
		this.bookService = bookService;
	}
	
	//SELECT ALL
	@Operation(summary = "List of all the books.")
	@GetMapping
	public ResponseEntity<?> getAllBooks(){
		
		List<BookDto> response = bookService.getBooks();
		return ResponseEntity.ok(response);
	}
	
	//SELECT BY ISBN
	@Operation(summary = "List of a book by ISBN.")
	@GetMapping("/{isbn}")
	public ResponseEntity<?> getBookByIsbn(@PathVariable String isbn){
		
		BookDto response = bookService.getBookByIsbn(isbn);
		return ResponseEntity.ok(response);
	}
	
	//CREATE
	@Operation(summary = "Add a book.")
	@ApiResponse(responseCode = "201", description = "Book was added successfully.")
	@ApiResponse(responseCode = "400", description = "Bad Request.")
	@PostMapping
	public ResponseEntity<?> createBook(@Valid @RequestBody BookDto dto){
		try {
			BookDto created = bookService.createBook(dto);
			return new ResponseEntity<>(created, HttpStatus.CREATED);
			
		} catch (Exception e){
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//UPDATE
	@Operation(summary = "Update a book.")
	@ApiResponse(responseCode = "200", description = "Book was updated successfully.")
	@ApiResponse(responseCode = "400", description = "Book was not found")
	@PutMapping("/{isbn}")
	public ResponseEntity<?> updateBook(@PathVariable String isbn, @Valid @RequestBody BookDto dto){
		try {
			BookDto updated = bookService.updateBook(isbn, dto);
			return new ResponseEntity<>(updated, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//DELETE
	@Operation(summary = "Delete a book.")
	@ApiResponse(responseCode = "200", description = "Book was deleted successfully.")
	@ApiResponse(responseCode = "404", description = "Book was not found.")
	@DeleteMapping("/{isbn}")
	public ResponseEntity<?> deleteBook(@PathVariable String isbn){
		try {
			bookService.deleteBook(isbn);
			return new ResponseEntity<>("Book has been deleted.", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//ANATHESI
	@Operation(summary = "Authors of a book.")
	@ApiResponse(responseCode = "200", description = "Authors listed successfully.")
	@ApiResponse(responseCode = "404", description = "Authors were not found.")
	@GetMapping("/{isbn}/authors")
	public ResponseEntity<?> getAuthorsOfBooks(@PathVariable String isbn){
		try {
			List<Integer> authorsIDs = bookService.getBookByIsbn(isbn).getAuthorsIds();
			return new ResponseEntity<>(authorsIDs, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
