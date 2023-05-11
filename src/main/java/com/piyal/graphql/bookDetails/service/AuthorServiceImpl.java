package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AuthorServiceImpl implements AuthorService{
	private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
	
	// Using as database for authors
	private final List<Author> authors = new ArrayList<>();
	
	@Override
	public String addAuthor(String firstName, String lastName) {
		UUID uuid = UUID.randomUUID();
		Author author = Author.builder()
				.id(uuid)
				.firstName(firstName)
				.lastName(lastName)
				.build();
		authors.add(author);
		logger.info("Author {} {} added!",firstName, lastName);
		return String.format("Author %s %s added!",firstName, lastName);
	}
	
	@Override
	public String removeAuthor(String firstName, String lastName) {
		Author authorToBeRemoved = authors.stream().filter(author ->
						author.firstName().equalsIgnoreCase(firstName) &&
								author.lastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
		if(authorToBeRemoved != null){
			authors.remove(authorToBeRemoved);
			logger.info("Author {} {} is removed from the database!", firstName, lastName);
			return String.format("Author %s %s is removed from the database!",firstName, lastName);
		}
		logger.info("Author {} {} doesn't exist or something went wrong.", firstName, lastName);
		return String.format("Author %s %s doesn't exist or something went wrong.",firstName, lastName);
	}
	
	@Override
	public Author getAuthorByName(String firstName, String lastName) {
		return authors.stream().filter(author ->
						author.firstName().equalsIgnoreCase(firstName) &&
								author.lastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public List<Author> getAuthors() {
		return authors;
	}
}
