package com.piyal.graphql.bookDetails.controller;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.service.AuthorServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * The controller class that handles the request of author related api
 */
@Controller
public class AuthorController {
	private final AuthorServiceImpl authorServiceImpl;
	
	AuthorController(AuthorServiceImpl authorServiceImpl) {
		this.authorServiceImpl = authorServiceImpl;
	}
	
	/**
	 * This method handles the request to get an Author
	 *
	 * @param firstName the first name of the author
	 * @param lastName  the last name of the author
	 * @return the author if found otherwise null
	 */
	@QueryMapping
	Author author(@Argument String firstName, @Argument String lastName) {
		return authorServiceImpl.getAuthorByName(firstName, lastName);
	}
	
	/**
	 * This method handles the request to get all the authors
	 *
	 * @return the list of the authors if at least one author exist otherwise empty list
	 */
	@QueryMapping
	List<Author> authors() {
		return authorServiceImpl.getAuthors();
	}
	
	/**
	 * This method handles the mutation request of an author. Simply, this method handle the request to add an Author
	 *
	 * @param firstName the first name of the author
	 * @param lastName  the last name of the author
	 * @return success or failure message to do so
	 */
	@MutationMapping
	String addAuthor(@Argument String firstName, @Argument String lastName) {
		return authorServiceImpl.addAuthor(firstName, lastName);
	}
	
	/**
	 * This method handles the mutation request of an author to remove the author
	 *
	 * @param firstName the first name of the author
	 * @param lastName  the last name of the author
	 * @return success or failure message
	 */
	@MutationMapping
	String removeAuthor(@Argument String firstName, @Argument String lastName) {
		return authorServiceImpl.removeAuthor(firstName, lastName);
	}
}
