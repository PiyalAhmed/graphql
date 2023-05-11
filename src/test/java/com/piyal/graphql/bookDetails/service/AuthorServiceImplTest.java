package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthorServiceImplTest {
	AuthorServiceImpl authorService;
	
	@BeforeEach
	void setUp() {
		authorService = new AuthorServiceImpl();
	}
	
	@AfterEach
	void tearDown() {
		authorService = null;
	}
	
	@Test
	@DisplayName("getAuthors() method of AuthorServiceImpl class")
	void getAuthors() {
		authorService.addAuthor("Demo", "Author");
		authorService.addAuthor("Demo", "Author2");
		assertFalse(authorService.getAuthors().isEmpty());
		assertEquals(2, authorService.getAuthors().size());
	}
	
	@Test
	@DisplayName("addAuthor() method of AuthorServiceImpl class")
	void addAuthor() {
		String message = authorService.addAuthor("Demo", "Author");
		assertEquals("Author Demo Author added!", message);
		assertEquals(1, authorService.getAuthors().size());
		authorService.addAuthor("Demo", "Author2");
		assertEquals(2, authorService.getAuthors().size());
	}
	
	@Test
	@DisplayName("removeAuthor() method of AuthorServiceImpl class")
	void removeAuthor() {
		String message = authorService.removeAuthor("Demo", "Author");
		assertEquals("Author Demo Author doesn't exist or something went wrong.", message);
		authorService.addAuthor("Demo", "Author");
		message = authorService.removeAuthor("Demo", "Author");
		assertEquals("Author Demo Author is removed from the database!", message);
		assertTrue(authorService.getAuthors().isEmpty());
	}
	
	@Test
	@DisplayName("getAuthorByName() method of AuthorServiceImpl class")
	void getAuthorByName() {
		Author author = authorService.getAuthorByName("Demo", "Author");
		assertNull(author);
		authorService.addAuthor("Demo", "Author");
		author = authorService.getAuthorByName("Demo", "Author");
		assertNotNull(author);
		assertEquals("Demo", author.firstName());
		assertEquals("Author", author.lastName());
	}
}