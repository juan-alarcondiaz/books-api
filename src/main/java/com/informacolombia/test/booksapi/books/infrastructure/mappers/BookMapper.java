package com.informacolombia.test.booksapi.books.infrastructure.mappers;

import com.informacolombia.test.booksapi.authors.infrastructure.adapters.output.oracle.AuthorEntity;
import com.informacolombia.test.booksapi.books.domain.models.Book;
import com.informacolombia.test.booksapi.books.infrastructure.adapters.output.oracle.BookEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface BookMapper {

    void updateBook(@MappingTarget Book target, BookEntity source);

    Book toBook(BookEntity bookEntity);

    List<Book> toBooks(List<BookEntity> bookEntities);

    @Mapping(target = "authors", source = "authorEntities")
    BookEntity toBookEntity(Book book, List<AuthorEntity> authorEntities);

    @Mapping(target = "authors", source = "authorEntities")
    void updateBookEntity(@MappingTarget BookEntity target, Book book, List<AuthorEntity> authorEntities);
}
