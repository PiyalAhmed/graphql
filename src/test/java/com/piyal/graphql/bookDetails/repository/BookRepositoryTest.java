package com.piyal.graphql.bookDetails.repository;

import com.piyal.graphql.bookDetails.model.Book;
import com.piyal.graphql.bookDetails.service.AuthorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BookRepositoryTest {
	
	private AuthorRepository authorRepository;
	private BookRepository bookRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		authorRepository = new AuthorRepository();
		AuthorServiceImpl authorService = new AuthorServiceImpl(authorRepository);
		bookRepository = new BookRepository(authorService);
	}
	
	@Test
	public void addBookTest() {
		authorRepository.addAuthor("John", "Doe");
		
		String bookName = "The Test Book";
		int bookPageCount = 200;
		String authorFirstName = "John";
		String authorLastName = "Doe";
		String expected = bookName + " by " + authorFirstName + " Doe is added!";
		
		String actual = bookRepository.addBook(bookName, bookPageCount, authorFirstName);
		
		assertEquals(expected, actual);
		List<Book> books = bookRepository.books();
		assertEquals(1, books.size());
		Book book = books.get(0);
		assertEquals(bookName, book.name());
		assertEquals(bookPageCount, book.pageCount());
		assertEquals(authorFirstName, book.author().firstName());
		assertEquals(authorLastName, book.author().lastName());
	}
	
	@Test
	public void addBookWithNonExistingAuthorTest() {
		String bookName = "The Test Book";
		int bookPageCount = 200;
		String authorFirstName = "John";
		String expected = "Author " + authorFirstName + " does not exist";
		
		String actual = bookRepository.addBook(bookName, bookPageCount, authorFirstName);
		
		assertEquals(expected, actual);
		List<Book> books = bookRepository.books();
		assertEquals(0, books.size());
	}
	
	@Test
	public void removeBookTest() {
		String bookName = "The Test Book";
		int bookPageCount = 200;
		String authorFirstName = "John";
		
		authorRepository.addAuthor(authorFirstName, "Doe");
		bookRepository.addBook(bookName, bookPageCount, authorFirstName);
		
		String expected = "Book, " + bookName + " is removed!";
		String actual = bookRepository.removeBook(bookName);
		
		assertEquals(expected, actual);
		List<Book> books = bookRepository.books();
		assertEquals(0, books.size());
	}
	
	@Test
	public void removeNonExistingBookTest() {
		String bookName = "The Test Book";
		String expected = "Book, " + bookName + " does not exist or something went wrong!";
		
		String actual = bookRepository.removeBook(bookName);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void bookTest() {
		String bookName = "The Test Book";
		int bookPageCount = 200;
		String authorFirstName = "John";
		String authorLastName = "Doe";
		
		authorRepository.addAuthor(authorFirstName, authorLastName);
		bookRepository.addBook(bookName, bookPageCount, authorFirstName);
		
		Book book = bookRepository.book(bookName);
		
		assertNotNull(book);
		assertEquals(bookName, book.name());
		assertEquals(bookPageCount, book.pageCount());
		assertEquals(authorFirstName, book.author().firstName());
		assertEquals(authorLastName, book.author().lastName());
	}
}
