package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Book;

import java.util.List;

public interface BookService {
	String addBook(String name, int pageCount, String authorFirstName);
	String removeBook(String name);
	Book book(String name);
	List<Book> books();
}
