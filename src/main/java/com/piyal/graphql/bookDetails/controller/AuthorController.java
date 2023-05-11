package com.piyal.graphql.bookDetails.controller;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.service.AuthorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;


@Controller
public class AuthorController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorController.class);
	private final AuthorServiceImpl authorServiceImpl;
	
	AuthorController(AuthorServiceImpl authorServiceImpl) {
		this.authorServiceImpl = authorServiceImpl;
	}
	
	@QueryMapping
	Author author(@Argument String firstName, @Argument String lastName) {
		logger.info("Getting Author {} {}...", firstName, lastName);
		return authorServiceImpl.getAuthorByName(firstName, lastName);
	}
	
	@QueryMapping
	List<Author> authors() {
		logger.info("Getting all authors...");
		return authorServiceImpl.getAuthors();
	}
	
	@MutationMapping
	String addAuthor(@Argument String firstName, @Argument String lastName) {
		logger.info("Adding author {} {}...", firstName, lastName);
		return authorServiceImpl.addAuthor(firstName, lastName);
	}
	
	@MutationMapping
	String removeAuthor(@Argument String firstName, @Argument String lastName) {
		logger.info("Removing author {} {}...", firstName, lastName);
		return authorServiceImpl.removeAuthor(firstName, lastName);
	}
}
