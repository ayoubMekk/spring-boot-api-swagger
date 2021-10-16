package com.mekkaoui.booksapi.repositories;

import com.mekkaoui.booksapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository  extends JpaRepository<Book, Long> {
    List<Book> getBookByAuthorId(Long id);
}
