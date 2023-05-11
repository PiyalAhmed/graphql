package com.piyal.graphql.bookDetails.controller;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.model.Book;
import com.piyal.graphql.bookDetails.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookControllerTest {
	
	@Mock
	private BookServiceImpl bookServiceImpl;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	public void testBook() {
		Author author = Author.builder()
				.id(UUID.randomUUID())
				.firstName("Test")
				.lastName("Author")
				.build();
		Book book = Book.builder()
				.name("Test Book")
				.pageCount(100)
				.author(author)
				.build();
		when(bookServiceImpl.book(anyString())).thenReturn(book);
		
		BookController bookController = new BookController(bookServiceImpl);
		Book result = bookController.book("Test Book");
		
		assertThat(result).isEqualTo(book);
	}
	
	@Test
	public void testBooks() {
		List<Book> bookList = new ArrayList<>();
		Author author = Author.builder()
				.id(UUID.randomUUID())
				.firstName("Test")
				.lastName("Author")
				.build();
		Book book1 = Book.builder()
				.name("Test Book 1")
				.pageCount(100)
				.author(author)
				.build();
		Book book2 = Book.builder()
				.name("Test Book 2")
				.pageCount(100)
				.author(author)
				.build();
		bookList.add(book1);
		bookList.add(book2);
		when(bookServiceImpl.books()).thenReturn(bookList);
		
		BookController bookController = new BookController(bookServiceImpl);
		List<Book> result = bookController.books();
		
		assertThat(result).isEqualTo(bookList);
	}
	
	@Test
	public void testAddBook() {
		when(bookServiceImpl.addBook(anyString(), anyInt(), anyString())).thenReturn("Book added successfully");
		
		BookController bookController = new BookController(bookServiceImpl);
		String result = bookController.addBook("Test Book", 100, "Test Author");
		
		assertThat(result).isEqualTo("Book added successfully");
	}
	
	@Test
	public void testRemoveBook() {
		when(bookServiceImpl.removeBook(anyString())).thenReturn("Book removed successfully");
		
		BookController bookController = new BookController(bookServiceImpl);
		String result = bookController.removeBook("Test Book");
		
		assertThat(result).isEqualTo("Book removed successfully");
	}
}
