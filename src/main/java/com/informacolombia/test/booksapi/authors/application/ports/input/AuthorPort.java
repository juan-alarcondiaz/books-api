package com.informacolombia.test.booksapi.authors.application.ports.input;

import com.informacolombia.test.booksapi.authors.domain.models.Author;

import java.util.List;

public interface AuthorPort {

    List<Author> getAll();
    Author getById(Long id);
    Author create(Author author);
    Author update(Author author, Long id);
    void delete(Long id);
}