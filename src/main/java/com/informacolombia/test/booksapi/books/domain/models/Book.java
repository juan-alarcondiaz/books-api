package com.informacolombia.test.booksapi.books.domain.models;

import com.informacolombia.test.booksapi.authors.domain.models.Author;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
public class Book {

    private Long id;

    private String title;

    private LocalDate publishDate;

    private Set<Author> authors;
}

