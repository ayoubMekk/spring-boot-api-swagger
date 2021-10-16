package com.mekkaoui.booksapi;

import com.mekkaoui.booksapi.entities.Author;
import com.mekkaoui.booksapi.entities.Book;
import com.mekkaoui.booksapi.services.AuthorService;
import com.mekkaoui.booksapi.services.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BooksApiApplication implements CommandLineRunner {

	private final BookService bookService;
	private final AuthorService authorService;

	public BooksApiApplication(BookService bookService, AuthorService authorService) {
		this.bookService = bookService;
		this.authorService = authorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(BooksApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Author author =  authorService.addAuthor(new Author(null, "Ayoub MEKKAOUI",null));
		bookService.addBook(new Book(null,"Introduction to Spring Boot framework", 199.99,5,author));
		bookService.addBook(new Book(null,"Angular by Examples", 89.0,10,author));
	}
}
