package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

class AuthorServiceImplTest {
	
	private AuthorServiceImpl authorService;
	
	@Mock
	private AuthorRepository authorRepository;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		authorService = new AuthorServiceImpl(authorRepository);
	}
	
	@Test
	void addAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		
		when(authorRepository.addAuthor(firstName, lastName)).thenReturn("Author John Doe added!");
		
		String result = authorService.addAuthor(firstName, lastName);
		
		assertEquals("Author John Doe added!", result);
	}
	
	@Test
	void removeAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		
		when(authorRepository.removeAuthor(firstName, lastName)).thenReturn("Author John Doe removed!");
		
		String result = authorService.removeAuthor(firstName, lastName);
		
		assertEquals("Author John Doe removed!", result);
	}
	
	@Test
	void getAuthorByName() {
		String firstName = "John";
		String lastName = "Doe";
		
		Author author = Author.builder()
				.firstName(firstName)
				.lastName(lastName)
				.build();
		
		when(authorRepository.getAuthorByName(firstName, lastName)).thenReturn(author);
		
		Author result = authorService.getAuthorByName(firstName, lastName);
		
		assertNotNull(result);
		assertEquals(firstName, result.firstName());
		assertEquals(lastName, result.lastName());
	}
	
	@Test
	void getAuthors() {
		List<Author> authors = new ArrayList<>();
		
		authors.add(Author.builder()
				.firstName("John")
				.lastName("Doe")
				.build());
		
		authors.add(Author.builder()
				.firstName("Jane")
				.lastName("Doe")
				.build());
		
		when(authorRepository.authors()).thenReturn(authors);
		
		List<Author> result = authorService.getAuthors();
		
		assertNotNull(result);
		assertEquals(authors.size(), result.size());
		assertEquals(authors.get(0).firstName(), result.get(0).firstName());
		assertEquals(authors.get(0).firstName(), result.get(0).firstName());
		assertEquals(authors.get(1).firstName(), result.get(1).firstName());
		assertEquals(authors.get(1).firstName(), result.get(1).firstName());
	}
}
