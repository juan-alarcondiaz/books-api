package com.informacolombia.test.booksapi.books.application.ports.output;

import com.informacolombia.test.booksapi.books.domain.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    boolean existsById(Long id);

    Book create(Book book);

    Book update(Book book);

    void deleteById(Long id);
}
