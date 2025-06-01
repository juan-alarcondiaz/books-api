package com.informacolombia.test.booksapi.books.infrastructure.adapters.output.oracle;

import com.informacolombia.test.booksapi.authors.infrastructure.adapters.output.oracle.AuthorEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "BOOKS")
@Setter
@Getter
public class BookEntity {

    @Id
    @Setter(AccessLevel.NONE)
    @Column(name = "BOOK_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq")
    @SequenceGenerator(name = "book_seq", sequenceName = "BOOK_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "PUBLISH_DATE", nullable = false)
    private LocalDate publishDate;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinTable(
            name = "BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"),
            uniqueConstraints =  @UniqueConstraint(columnNames = {"AUTHOR_ID", "BOOK_ID"})
    )
    private List<AuthorEntity> authors;
}