package com.piyal.graphql.bookDetails.service;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService {
	private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
	
	// Using as database for books
	private final List<Book> books = new ArrayList<>();
	
	AuthorService authorService;
	
	BookServiceImpl(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@Override
	public String addBook(String name, int pageCount, String authorFirstName) {
		UUID id = UUID.randomUUID();
		Author author = authorService.getAuthors().stream().filter(a ->
						a.firstName().equalsIgnoreCase(authorFirstName))
				.findFirst()
				.orElse(null);
		
		if (author != null) {
			Book book = Book.builder()
					.id(id)
					.name(name)
					.pageCount(pageCount)
					.author(author)
					.build();
			books.add(book);
			logger.info("{} by {} {} is added!", name, author.firstName(), author.lastName());
			return String.format("%s by %s %s is added!", name, author.firstName(), author.lastName());
		}
		logger.info("Author {} does not exist", authorFirstName);
		return String.format("Author %s does not exist", authorFirstName);
	}
	
	@Override
	public String removeBook(String name) {
		Book bookToBeRemoved = books.stream().filter(book ->
						book.name().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
		if (bookToBeRemoved != null) {
			books.remove(bookToBeRemoved);
			logger.info("Book, {} is removed!", name);
			return String.format("Book, %s is removed!", name);
		}
		logger.info("Book, {} does not exist or something went wrong!", name);
		return String.format("Book, %s does not exist or something went wrong!", name);
	}
	
	@Override
	public Book book(String name) {
		return books.stream().filter(book ->
						book.name().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}
	
	@Override
	public List<Book> books() {
		return books;
	}
}
