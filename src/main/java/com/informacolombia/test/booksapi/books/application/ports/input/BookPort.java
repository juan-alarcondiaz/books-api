package com.informacolombia.test.booksapi.books.application.ports.input;

import com.informacolombia.test.booksapi.books.domain.models.Book;

import java.util.List;

public interface BookPort {
    List<Book> getAll();
    Book getById(Long id);
    Book create(Book book);
    Book update(Book book, Long id);
    void delete(Long id);
}
