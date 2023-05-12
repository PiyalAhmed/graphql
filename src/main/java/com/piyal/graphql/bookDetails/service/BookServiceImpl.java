package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Book;
import com.piyal.graphql.bookDetails.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementation of the {@link BookService} interface
 */
@Service
public class BookServiceImpl implements BookService {
	private final BookRepository bookRepository;
	
	BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	/**
	 * This method adds a book to the database
	 * @param name the name of the book
	 * @param pageCount total page count of the book
	 * @param authorFirstName the first name of the author of the book
	 * @return success message
	 */
	@Override
	public String addBook(String name, int pageCount, String authorFirstName) {
		return bookRepository.addBook(name, pageCount, authorFirstName);
	}
	
	/**
	 * This method remove a book from the database
	 * @param name the name of the book
	 * @return success or failure message
	 */
	@Override
	public String removeBook(String name) {
		return bookRepository.removeBook(name);
	}
	
	/**
	 * This method retrieve a Book from the database
	 * @param name the name of the book
	 * @return the book retrieved or null if not exist
	 */
	@Override
	public Book book(String name) {
		return bookRepository.book(name);
	}
	
	/**
	 * This method returns all the books existing in the database
	 * @return list of books
	 */
	@Override
	public List<Book> books() {
		return bookRepository.books();
	}
}
