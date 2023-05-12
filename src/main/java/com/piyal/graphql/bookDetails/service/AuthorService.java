package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;

import java.util.List;

/**
 * This interface provides the services related to authors
 */
public interface AuthorService {
	/**
	 * This method adds an Author to the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return success message
	 */
	String addAuthor(String firstName, String lastName);
	
	/**
	 * This method removes an Author from the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return success or failure message
	 */
	String removeAuthor(String firstName, String lastName);
	
	/**
	 * This method retrieve an Author from the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return the author retrieved or null if not exist
	 */
	Author getAuthorByName(String firstName, String lastName);
	
	/**
	 * This method returns all the authors existing in the database
	 * @return list of authors
	 */
	List<Author> getAuthors();
}
