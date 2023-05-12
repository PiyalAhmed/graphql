package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementation of the {@link AuthorService} interface
 */
@Service
public class AuthorServiceImpl implements AuthorService {
	private final AuthorRepository authorRepository;
	
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}
	
	/**
	 * This method adds an Author to the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return success message
	 */
	@Override
	public String addAuthor(String firstName, String lastName) {
		return authorRepository.addAuthor(firstName, lastName);
	}
	
	/**
	 * This method removes an Author from the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return success or failure message
	 */
	@Override
	public String removeAuthor(String firstName, String lastName) {
		return authorRepository.removeAuthor(firstName, lastName);
	}
	
	/**
	 * This method retrieve an Author from the database
	 * @param firstName of the author
	 * @param lastName of the author
	 * @return the author retrieved or null if not exist
	 */
	@Override
	public Author getAuthorByName(String firstName, String lastName) {
		return authorRepository.getAuthorByName(firstName, lastName);
	}
	
	/**
	 * This method return all the authors existing in the database
	 * @return list of authors
	 */
	@Override
	public List<Author> getAuthors() {
		return authorRepository.authors();
	}
}
