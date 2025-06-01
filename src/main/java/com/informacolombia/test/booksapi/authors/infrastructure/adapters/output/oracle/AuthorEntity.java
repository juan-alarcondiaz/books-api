package com.informacolombia.test.booksapi.authors.infrastructure.adapters.output.oracle;

import com.informacolombia.test.booksapi.books.infrastructure.adapters.output.oracle.BookEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "AUTHORS")
@Setter
@Getter
public class AuthorEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "AUTHOR_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_seq")
    @SequenceGenerator(name = "author_seq", sequenceName = "AUTHOR_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @ManyToMany(mappedBy = "authors")
    private List<BookEntity> books;
}
