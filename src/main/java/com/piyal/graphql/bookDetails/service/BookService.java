package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Book;

import java.util.List;

/**
 * This interface provides the services related to books
 */
public interface BookService {
	/**
	 * This method adds a book to the database
	 * @param name the name of the book
	 * @param pageCount total page count of the book
	 * @param authorFirstName the first name of the author of the book
	 * @return success message
	 */
	String addBook(String name, int pageCount, String authorFirstName);
	
	/**
	 * This method remove a book from the database
	 * @param name the name of the book
	 * @return success or failure message
	 */
	String removeBook(String name);
	
	/**
	 * This method retrieve a Book from the database
	 * @param name the name of the book
	 * @return the book retrieved or null if not exist
	 */
	Book book(String name);
	
	/**
	 * This method returns all the books existing in the database
	 * @return list of books
	 */
	List<Book> books();
}
