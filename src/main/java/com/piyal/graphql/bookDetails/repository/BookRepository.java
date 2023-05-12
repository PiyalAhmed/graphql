package com.piyal.graphql.bookDetails.repository;

import com.piyal.graphql.bookDetails.model.Author;
import com.piyal.graphql.bookDetails.model.Book;
import com.piyal.graphql.bookDetails.service.AuthorServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The repository for book. this class holds the database of the book
 */
@Repository
public class BookRepository {
	private final Logger logger = LoggerFactory.getLogger(BookRepository.class);
	
	// Acting as a fake database of Books
	private final List<Book> books;
	private final AuthorServiceImpl authorService;
	BookRepository(AuthorServiceImpl authorService){
		this.books = new ArrayList<>();
		this.authorService = authorService;
	}
	
	/**
	 * This method adds a book in the database
	 *
	 * @param name book name
	 * @param pageCount page count of the book
	 * @param authorFirstName first name of the author of that book
	 * @return success or failure message
	 */
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
	
	/**
	 * This method removes a book from the database
	 * @param name the name of the book to be removed
	 * @return success or failure message
	 */
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
	
	/**
	 * This method retrieve a book from the database
	 * @param name the name of the book
	 * @return the book if found otherwise null
	 */
	public Book book(String name) {
		return books.stream().filter(book ->
						book.name().equalsIgnoreCase(name))
				.findFirst()
				.orElse(null);
	}
	
	/**
	 * This method retrieves all the books from the database
	 * @return the list of the books existing in the database
	 */
	public List<Book> books() {
		return books;
	}
	
}
