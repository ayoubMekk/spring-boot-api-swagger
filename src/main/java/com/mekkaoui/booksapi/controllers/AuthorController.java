package com.mekkaoui.booksapi.controllers;

import com.mekkaoui.booksapi.entities.Author;
import com.mekkaoui.booksapi.services.AuthorService;
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
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Operation(operationId = "getAllAuthors", summary = "Get All authors", tags = {"GET"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get authors"),
            @ApiResponse(responseCode = "404", description = "No author available")
    })
    @GetMapping("authors")
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author>  authors = authorService.getAuthors();
        if (authors.size() != 0){
            return new ResponseEntity<>(authors, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(404));
    }


    @Operation(operationId = "addAuthor", summary = "Add new author", tags = {"POST"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    @PostMapping("authors")
    public ResponseEntity addAuthor(@RequestBody Author author){
        try {
            authorService.addAuthor(author);
            return new ResponseEntity<>(HttpStatus.valueOf(201));
        } catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.valueOf(500));
        }
    }

    @Operation(operationId = "getAuthorById", summary = "Get author by ID", tags = {"GET"},
            parameters = { @Parameter(in = ParameterIn.PATH, name = "id", description = "Author Id") })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the author"),
            @ApiResponse(responseCode = "400", description = "Invalid author ID"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("authors/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id){
        Author author = authorService.getAuthorById(id);
        if (author != null){
            return new ResponseEntity<>(author, HttpStatus.valueOf(200));
        }
        return new ResponseEntity<>(null, HttpStatus.valueOf(404));
    }

}
