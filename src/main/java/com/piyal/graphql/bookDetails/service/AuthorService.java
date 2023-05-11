package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;

import java.util.List;


public interface AuthorService {
	String addAuthor(String firstName, String lastName);
	String removeAuthor(String firstName, String lastName);
	Author getAuthorByName(String firstName, String lastName);
	List<Author> getAuthors();
}
