package com.informacolombia.test.booksapi.books.application.mappers;

import com.informacolombia.test.booksapi.books.domain.models.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface BookUpdaterMapper {

    @Mapping(target = "id", ignore = true)
    void updateBook(@MappingTarget Book target, Book source);
}
