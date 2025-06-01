package com.informacolombia.test.booksapi.authors.application.adapters;

import com.informacolombia.test.booksapi.authors.application.mappers.AuthorUpdaterMapper;
import com.informacolombia.test.booksapi.authors.application.ports.input.AuthorPort;
import com.informacolombia.test.booksapi.authors.application.ports.output.AuthorRepository;
import com.informacolombia.test.booksapi.authors.domain.exception.AuthorDeletionException;
import com.informacolombia.test.booksapi.authors.domain.exception.AuthorNotFoundException;
import com.informacolombia.test.booksapi.authors.domain.models.Author;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorAdapter implements AuthorPort {

    private final AuthorRepository authorRepository;
    private final AuthorUpdaterMapper authorUpdaterMapper;

    @Override
    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException("Author not found"));
    }

    @Override
    public Author create(Author author) {
        return authorRepository.create(author);
    }

    @Override
    public Author update(@NotNull Author author, Long id) {
        try {
            Author existingAuthor = this.getById(id);
            authorUpdaterMapper.updateAuthor(existingAuthor, author);
            return authorRepository.update(existingAuthor);
        } catch (AuthorNotFoundException e) {
            throw new AuthorNotFoundException("Cannot update: Author with ID " + id + " does not exist.");
        }
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException("Cannot delete: Author with ID " + id + " does not exist.");
        }
        if (authorRepository.existsBooksByAuthorId(id)) {
            throw new AuthorDeletionException("Cannot delete author with ID " + id + " because there are associated books.");
        }
        authorRepository.deleteById(id);
    }
}
