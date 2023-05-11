package com.piyal.graphql.bookDetails.controller;

import com.piyal.graphql.bookDetails.model.Book;
import com.piyal.graphql.bookDetails.service.BookServiceImpl;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {
	private final BookServiceImpl bookServiceImpl;
	
	BookController(BookServiceImpl bookServiceImpl) {
		this.bookServiceImpl = bookServiceImpl;
	}
	
	@QueryMapping
	Book book(@Argument String name) {
		return bookServiceImpl.book(name);
	}
	
	@QueryMapping
	List<Book> books() {
		return bookServiceImpl.books();
	}
	
	@MutationMapping
	String addBook(@Argument String name, @Argument int pageCount, @Argument String authorFirstName) {
		return bookServiceImpl.addBook(name, pageCount, authorFirstName);
	}
	
	@MutationMapping
	String removeBook(@Argument String name) {
		return bookServiceImpl.removeBook(name);
	}
}
