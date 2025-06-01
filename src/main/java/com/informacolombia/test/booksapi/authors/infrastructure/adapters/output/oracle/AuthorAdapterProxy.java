package com.informacolombia.test.booksapi.authors.infrastructure.adapters.output.oracle;

import com.informacolombia.test.booksapi.authors.application.ports.output.AuthorRepository;
import com.informacolombia.test.booksapi.authors.domain.models.Author;
import com.informacolombia.test.booksapi.authors.infrastructure.mappers.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorAdapterProxy implements AuthorRepository {

    private final AuthorJpaRepository authorJpaRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<Author> findAll() {
        return authorMapper.toAuthors(authorJpaRepository.findAll());
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorJpaRepository.findById(id).map(authorMapper::toAuthor);
    }

    @Override
    public boolean existsById(Long id) {
        return authorJpaRepository.existsById(id);
    }

    @Override
    public boolean existsBooksByAuthorId(Long id) {
        return authorJpaRepository.existsBooksByAuthorId(id);
    }

    @Override
    public Author create(Author author) {
        AuthorEntity authorEntity = authorMapper.toAuthorEntity(author);
        return saveAndFlush(authorEntity, author);
    }

    @Override
    public Author update(@NotNull Author author) {
        AuthorEntity authorEntity = authorJpaRepository.getReferenceById(author.getId());
        authorMapper.updateAuthorEntity(authorEntity, author);
        return saveAndFlush(authorEntity, author);
    }

    @Override
    public void deleteById(Long id) {
        authorJpaRepository.deleteById(id);
    }

    private Author saveAndFlush(AuthorEntity authorEntity, Author author) {
        AuthorEntity authorEntitySaved = authorJpaRepository.save(authorEntity);
        authorMapper.updateAuthor(author, authorEntitySaved);
        return author;
    }
}
