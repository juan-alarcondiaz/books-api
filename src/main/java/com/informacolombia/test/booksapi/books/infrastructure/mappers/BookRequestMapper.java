package com.informacolombia.test.booksapi.books.infrastructure.mappers;

import com.informacolombia.test.booksapi.authors.domain.models.Author;
import com.informacolombia.test.booksapi.books.domain.models.Book;
import com.informacolombia.test.booksapi.books.infrastructure.dto.BookDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookRequestMapper {

    Book toBook(BookDTO bookDTO);

    @Mapping(target = "id", source = ".")
    Author toAuthor(Long id);
}
