package com.informacolombia.test.booksapi.authors.domain.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Author {

    private Long id;

    private String firstName;

    private String lastName;
}
