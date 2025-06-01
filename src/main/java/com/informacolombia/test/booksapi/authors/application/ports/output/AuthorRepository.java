package com.informacolombia.test.booksapi.authors.application.ports.output;

import com.informacolombia.test.booksapi.authors.domain.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> findAll();

    Optional<Author> findById(Long id);

    boolean existsById(Long id);

    boolean existsBooksByAuthorId(Long id);

    Author create(Author author);

    Author update(Author author);

    void deleteById(Long id);
}
