package com.informacolombia.test.booksapi.authors.infrastructure.mappers;

import com.informacolombia.test.booksapi.authors.domain.models.Author;
import com.informacolombia.test.booksapi.authors.infrastructure.adapters.output.oracle.AuthorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface AuthorMapper {

    void updateAuthor(@MappingTarget Author target, AuthorEntity source);

    Author toAuthor(AuthorEntity authorEntity);

    List<Author> toAuthors(List<AuthorEntity> authorEntities);

    AuthorEntity toAuthorEntity(Author author);

    void updateAuthorEntity(@MappingTarget AuthorEntity target, Author author);
}
