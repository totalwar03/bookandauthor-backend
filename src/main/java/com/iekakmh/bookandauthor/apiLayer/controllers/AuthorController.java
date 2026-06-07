package com.iekakmh.bookandauthor.apiLayer.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.iekakmh.bookandauthor.dataAccessLayer.models.AuthorDto;
import com.iekakmh.bookandauthor.dataAccessLayer.services.AuthorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/authors")
@Tag(name = "Authors", description = "Authors management.")
public class AuthorController {
	
	private final  AuthorService authorService;

	public AuthorController(AuthorService authorService) {
	
		this.authorService = authorService;
	}
	
	//SELECT ALL
	@Operation(summary = "List all authors.")
	@GetMapping
	public ResponseEntity<?> getAllAuthors(){
		List<AuthorDto> response = authorService.getAuthors();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	//SELECT BY ID
	@Operation(summary = "Find an author.")
	@ApiResponse(responseCode = "200", description = "Author found.")
	@ApiResponse(responseCode = "404", description = "Author was not found.")
	@GetMapping("/{id}")
	public ResponseEntity<?> getAuthorById(@PathVariable int id){
		try {
			AuthorDto response = authorService.getAuthorById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//CREATE
	@Operation(summary = "Add an author.")
	@ApiResponse(responseCode = "201", description = "Author add successfully")
	@ApiResponse(responseCode = "400", description = "Bad Request")
	@PostMapping
	public ResponseEntity<?> createAuthor(@Valid @RequestBody AuthorDto dto){
		try {
			AuthorDto created = authorService.createAuthor(dto);
			return new ResponseEntity<>(created, HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//UPDATE
	@Operation(summary = "Update an author.")
	@ApiResponse(responseCode = "200", description = "Author updated successfully")
	@ApiResponse(responseCode = "404", description = "Author was not found.")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAuthor(@PathVariable int id, @Valid @RequestBody AuthorDto dto){
		try {
			AuthorDto updated = authorService.updateAuthor(id, dto);
			return new ResponseEntity<>(updated, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//DELETE 
	@Operation(summary = "Delete an author.")
	@ApiResponse(responseCode = "200", description = "Author deleted successfully.")
	@ApiResponse(responseCode = "404", description = "Author was not foubd.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAuthor(@PathVariable int id){
		try {
			authorService.deleteAuthor(id);
			return new ResponseEntity<>("Author has been deleted.", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	//SELECT BOOKS FROM AUTHOR
	@Operation(summary = "Find books of an author.")
	@ApiResponse(responseCode = "200", description = "Books found successfully.")
	@ApiResponse(responseCode = "404", description = "Books were not found.")
	@GetMapping("/{id}/books")
	public ResponseEntity<?> getBooksFromAuthor(@PathVariable int id){
		try {
			List<String> authorBooks = authorService.getAuthorById(id).getBookIsbn();
			return new ResponseEntity<>(authorBooks, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
