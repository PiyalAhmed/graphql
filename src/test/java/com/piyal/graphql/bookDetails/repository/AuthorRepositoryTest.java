package com.piyal.graphql.bookDetails.repository;

import com.piyal.graphql.bookDetails.model.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthorRepositoryTest {
	private AuthorRepository authorRepository;
	
	@BeforeEach
	void setUp() {
		authorRepository = new AuthorRepository();
	}
	
	@Test
	void addAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		String expected = String.format("Author %s %s added!", firstName, lastName);
		String actual = authorRepository.addAuthor(firstName, lastName);
		assertEquals(expected, actual);
		assertEquals(1, authorRepository.authors().size());
		assertEquals(firstName, authorRepository.authors().get(0).firstName());
		assertEquals(lastName, authorRepository.authors().get(0).lastName());
	}
	
	@Test
	void removeAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		authorRepository.addAuthor(firstName, lastName);
		String expected = String.format("Author %s %s is removed from the database!", firstName, lastName);
		String actual = authorRepository.removeAuthor(firstName, lastName);
		assertEquals(expected, actual);
		assertEquals(0, authorRepository.authors().size());
	}
	
	@Test
	void removeNonExistentAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		String expected = String.format("Author %s %s doesn't exist or something went wrong.", firstName, lastName);
		String actual = authorRepository.removeAuthor(firstName, lastName);
		assertEquals(expected, actual);
		assertEquals(0, authorRepository.authors().size());
	}
	
	@Test
	void getAuthorByName() {
		String firstName = "John";
		String lastName = "Doe";
		authorRepository.addAuthor(firstName, lastName);
		Author expected = Author.builder()
				.firstName(firstName)
				.lastName(lastName)
				.build();
		Author actual = authorRepository.getAuthorByName(firstName, lastName);
		assertEquals(expected.firstName(), actual.firstName());
		assertEquals(expected.lastName(), actual.lastName());
	}
	
	@Test
	void getNonExistentAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		Author actual = authorRepository.getAuthorByName(firstName, lastName);
		assertNull(actual);
	}
}
