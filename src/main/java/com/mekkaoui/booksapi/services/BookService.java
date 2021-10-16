package com.mekkaoui.booksapi.services;

import com.mekkaoui.booksapi.entities.Book;
import com.mekkaoui.booksapi.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void  addBook(Book book){
        bookRepository.save(book);
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBookByAuthorId(Long id){
        return bookRepository.getBookByAuthorId(id);
    }
}
