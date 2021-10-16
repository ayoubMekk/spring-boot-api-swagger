package com.mekkaoui.booksapi.controllers;

import com.mekkaoui.booksapi.entities.Book;
import com.mekkaoui.booksapi.services.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(operationId = "getAllBooks", summary = "Get All books", tags = {"GET"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get books"),
            @ApiResponse(responseCode = "404", description = "No book available")
    })
    @GetMapping("books")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = bookService.getBooks();
        if (books.size() != 0){
            return new ResponseEntity<>(books, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(404));
    }


    @Operation(operationId = "addBook", summary = "Add new book", tags = {"POST"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("books")
    public ResponseEntity addBook(@RequestBody Book book){
        try {
            bookService.addBook(book);
            return new ResponseEntity<>(HttpStatus.valueOf(201));
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @Operation(operationId = "getBookByAuthorId", summary = "Get book by author ID", tags = {"GET"},
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Author Id") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get author's books"),
            @ApiResponse(responseCode = "400", description = "Invalid author ID"),
            @ApiResponse(responseCode = "404", description = "No book available")
    })
    @GetMapping("books/author/{id}")
    public ResponseEntity<List<Book>> getBookByAuthorId(@PathVariable Long id){
        List<Book> books = bookService.getBookByAuthorId(id);
        if (books.size() != 0){
            return new ResponseEntity<>(books, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(404));
    }

    @Operation(operationId = "getBookById", summary = "Get book by ID", tags = {"GET"},
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Book Id") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the book"),
            @ApiResponse(responseCode = "400", description = "Invalid Book ID"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        if (book != null){
            return new ResponseEntity<>(book, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(404));
    }

}
