package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.model.Book;
import com.piyal.graphql.bookDetails.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceImplTest {
	
	@Mock
	private BookRepository bookRepository;
	
	
	private BookServiceImpl bookService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
		bookService = new BookServiceImpl(bookRepository);
		
	}
	
	@Test
	public void testAddBook() {
		String bookName = "Test Book";
		int pageCount = 100;
		String authorFirstName = "Test";
		String expectedResult = "Book " + bookName + " added successfully!";
		when(bookRepository.addBook(bookName, pageCount, authorFirstName)).thenReturn(expectedResult);
		
		assertEquals(expectedResult, bookService.addBook(bookName, pageCount, authorFirstName));
	}
	
	@Test
	public void testRemoveBook() {
		String bookName = "Test Book";
		String expectedResult = "Book " + bookName + " removed successfully!";
		when(bookRepository.removeBook(bookName)).thenReturn(expectedResult);
		
		assertEquals(expectedResult, bookService.removeBook(bookName));
	}
	
	@Test
	public void testBook() {
		String bookName = "Test Book";
		Author author = Author.builder()
				.firstName("Test")
				.lastName("Author")
				.build();

		Book expectedBook = Book.builder()
				.name(bookName)
				.pageCount(100)
				.author(author)
				.build();
		when(bookRepository.book(bookName)).thenReturn(expectedBook);
		
		assertEquals(expectedBook, bookService.book(bookName));
	}
	
	@Test
	public void testBooks() {
		List<Book> expectedBooks = new ArrayList<>();
		Author author = Author.builder()
				.firstName("Test")
				.lastName("Author")
				.build();
		
		Book book1 = Book.builder()
				.name("Book 1")
				.pageCount(100)
				.author(author)
				.build();
		Book book2 = Book.builder()
				.name("Book 2")
				.pageCount(200)
				.author(author)
				.build();
		expectedBooks.add(book1);
		expectedBooks.add(book2);
		when(bookRepository.books()).thenReturn(expectedBooks);
		
		assertEquals(expectedBooks, bookService.books());
	}
	
}
