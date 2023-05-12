package com.piyal.graphql.bookDetails.repository;

import com.piyal.graphql.bookDetails.model.Author;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The repository for author. this class holds the database of the author
 */
@Repository
public class AuthorRepository {
	private final Logger logger = LoggerFactory.getLogger(AuthorRepository.class);
	
	/**
	 * This list of authors is acting as the fake database
	 */
	private final List<Author> authors;
	AuthorRepository(){
		this.authors = new ArrayList<>();
	}
	
	/**
	 * This method adds an Author to the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return success message
	 */
	public String addAuthor(String firstName, String lastName){
		UUID uuid = UUID.randomUUID();
		Author author = Author.builder()
				.id(uuid)
				.firstName(firstName)
				.lastName(lastName)
				.build();
		
		authors.add(author);
		logger.info("Author {} {} added!", firstName, lastName);
		return String.format("Author %s %s added!", firstName, lastName);
	}
	
	/**
	 * This method removes an Author from the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return success or failure message
	 */
	public String removeAuthor(String firstName, String lastName){
		Author authorToBeRemoved = authors.stream().filter(author ->
						author.firstName().equalsIgnoreCase(firstName) &&
								author.lastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
		
		if (authorToBeRemoved != null) {
			authors.remove(authorToBeRemoved);
			logger.info("Author {} {} is removed from the database!", firstName, lastName);
			return String.format("Author %s %s is removed from the database!", firstName, lastName);
		}
		logger.info("Author {} {} doesn't exist or something went wrong.", firstName, lastName);
		return String.format("Author %s %s doesn't exist or something went wrong.", firstName, lastName);
	}
	
	/**
	 * This method retrieve an Author from the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return the author retrieved or null if not exist
	 */
	public Author getAuthorByName(String firstName, String lastName) {
		return authors.stream().filter(author ->
						author.firstName().equalsIgnoreCase(firstName) &&
								author.lastName().equalsIgnoreCase(lastName))
				.findFirst()
				.orElse(null);
	}
	
	/**
	 * This method return all the authors existing in the database
	 * @return list of authors
	 */
	public List<Author> authors(){
		return authors;
	}
}
