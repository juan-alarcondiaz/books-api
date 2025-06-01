package com.informacolombia.test.booksapi.authors.infrastructure.mappers;

import com.informacolombia.test.booksapi.authors.domain.models.Author;
import com.informacolombia.test.booksapi.authors.infrastructure.dto.AuthorDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AuthorRequestMapper {

    Author toAuthor(AuthorDTO authorDTO);
}
