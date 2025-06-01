package com.informacolombia.test.booksapi.authors.application.mappers;

import com.informacolombia.test.booksapi.authors.domain.models.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface AuthorUpdaterMapper {

    @Mapping(target = "id", ignore = true)
    void updateAuthor(@MappingTarget Author target, Author source);
}
