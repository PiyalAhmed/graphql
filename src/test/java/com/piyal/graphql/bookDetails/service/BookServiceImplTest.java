package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class BookServiceImplTest {
	AuthorServiceImpl authorService;
	BookServiceImpl bookService;
	
	@BeforeEach
	void setUp() {
		authorService = new AuthorServiceImpl();
		bookService = new BookServiceImpl(authorService);
	}
	
	@AfterEach
	void tearDown() {
		authorService = null;
		bookService = null;
	}
	
	@Test
	void addBook() {
		String message = bookService.addBook("Demo Book", 100, "Demo");
		assertEquals("Author Demo does not exist", message);
		authorService.addAuthor("Demo", "Author");
		message = bookService.addBook("Demo Book", 100, "Demo");
		;
		assertEquals("Demo Book by Demo Author is added!", message);
	}
	
	@Test
	void removeBook() {
		authorService.addAuthor("Demo", "Author");
		bookService.addBook("Demo Book", 100, "Demo");
		String message = bookService.removeBook("Demo Book");
		assertEquals("Book, Demo Book is removed!", message);
		message = bookService.removeBook("Demo Book");
		assertEquals("Book, Demo Book does not exist or something went wrong!", message);
		
	}
	
	@Test
	void book() {
		authorService.addAuthor("Demo", "Author");
		bookService.addBook("Demo Book", 100, "Demo");
		Book demoBook = bookService.book("Demo Book");
		assertEquals("Demo Book", demoBook.name());
		assertEquals(100, demoBook.pageCount());
		assertEquals("Demo", demoBook.author().firstName());
		assertEquals("Author", demoBook.author().lastName());
	}
	
	@Test
	void books() {
		authorService.addAuthor("Demo", "Author");
		bookService.addBook("Demo Book", 100, "Demo");
		bookService.addBook("Demo Book2", 200, "Demo");
		assertFalse(bookService.books().isEmpty());
		assertEquals(2, bookService.books().size());
	}
}