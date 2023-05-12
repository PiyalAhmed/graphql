package com.piyal.graphql.bookDetails.controller;

import com.piyal.graphql.bookDetails.model.Book;
import com.piyal.graphql.bookDetails.service.BookServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * The controller class that handles the request of book related api
 */
@Controller
public class BookController {
	private final BookServiceImpl bookServiceImpl;
	
	BookController(BookServiceImpl bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}
	
	/**
	 * This method handles the request to get a Book
	 *
	 * @param name the name of the book
	 * @return the book if found otherwise null
	 */
	@QueryMapping
	Book book(@Argument String name) {
		return bookServiceImpl.book(name);
	}
	
	/**
	 * This method handles the request to get all the books
	 *
	 * @return the list of the books if at least one book exist otherwise empty list
	 */
	@QueryMapping
	List<Book> books() {
		return bookServiceImpl.books();
	}
	
	/**
	 * This method handles the mutation request of a book. Simply, this method handle the request to add a Book
	 *
	 * @param name            the name of the book
	 * @param pageCount       the total page count of the book
	 * @param authorFirstName the first name of the author of the book
	 * @return success or failure message to do so
	 */
	@MutationMapping
	String addBook(@Argument String name, @Argument int pageCount, @Argument String authorFirstName) {
		return bookServiceImpl.addBook(name, pageCount, authorFirstName);
	}
	
	/**
	 * This method handles the mutation request of a book to remove the book
	 *
	 * @param name the name of the book to be removed
	 * @return success or failure message
	 */
	@MutationMapping
	String removeBook(@Argument String name) {
		return bookServiceImpl.removeBook(name);
	}
}
