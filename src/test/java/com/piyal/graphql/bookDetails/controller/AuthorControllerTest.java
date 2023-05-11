package com.piyal.graphql.bookDetails.controller;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.service.AuthorServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AuthorControllerTest {
	@InjectMocks
	AuthorController authorController;
	
	@Mock
	AuthorServiceImpl authorServiceImpl;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void testAuthor() {
		Author author = Author.builder()
				.id(UUID.randomUUID())
				.firstName("John")
				.lastName("Doe")
				.build();
		
		Mockito.when(authorServiceImpl.getAuthorByName(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn(author);
		
		Author result = authorController.author("John", "Doe");
		
		Assertions.assertEquals(author, result);
		Mockito.verify(authorServiceImpl, Mockito.times(1)).getAuthorByName("John", "Doe");
	}
	
	@Test
	void testAuthors() {
		List<Author> authorList = new ArrayList<>();
		Author author1 = Author.builder()
				.id(UUID.randomUUID())
				.firstName("John")
				.lastName("Doe")
				.build();
		Author author2 = Author.builder()
				.id(UUID.randomUUID())
				.firstName("Jane")
				.lastName("Doe")
				.build();
		authorList.add(author1);
		authorList.add(author2);
		
		Mockito.when(authorServiceImpl.getAuthors()).thenReturn(authorList);
		
		List<Author> result = authorController.authors();
		
		Assertions.assertEquals(authorList, result);
		Mockito.verify(authorServiceImpl, Mockito.times(1)).getAuthors();
	}
	
	@Test
	void testAddAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		Mockito.when(authorServiceImpl.addAuthor(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn("Author added successfully");
		
		String result = authorController.addAuthor(firstName, lastName);
		
		Assertions.assertEquals("Author added successfully", result);
		Mockito.verify(authorServiceImpl, Mockito.times(1)).addAuthor("John", "Doe");
	}
	
	@Test
	void testRemoveAuthor() {
		String firstName = "John";
		String lastName = "Doe";
		Mockito.when(authorServiceImpl.removeAuthor(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
				.thenReturn("Author removed successfully");
		
		String result = authorController.removeAuthor(firstName, lastName);
		
		Assertions.assertEquals("Author removed successfully", result);
		Mockito.verify(authorServiceImpl, Mockito.times(1)).removeAuthor("John", "Doe");
	}
}
