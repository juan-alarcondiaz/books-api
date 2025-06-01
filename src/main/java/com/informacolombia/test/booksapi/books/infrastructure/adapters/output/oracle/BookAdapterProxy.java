package com.informacolombia.test.booksapi.books.infrastructure.adapters.output.oracle;

import com.informacolombia.test.booksapi.authors.infrastructure.adapters.output.oracle.AuthorEntity;
import com.informacolombia.test.booksapi.books.application.ports.output.BookRepository;
import com.informacolombia.test.booksapi.books.domain.models.Book;
import com.informacolombia.test.booksapi.books.infrastructure.mappers.BookMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookAdapterProxy implements BookRepository {

    @PersistenceContext
    EntityManager entityManager;

    private final BookJpaRepository bookJpaRepository;
    private final BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.toBooks(bookJpaRepository.findAll());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookJpaRepository.findById(id).map(bookMapper::toBook);
    }

    @Override
    public boolean existsById(Long id) {
        return bookJpaRepository.existsById(id);
    }

    @Override
    public Book create(Book book) {
        BookEntity bookEntity = bookMapper.toBookEntity(book, getReferenceAuthorEntities(book));
        return saveAndFlush(bookEntity, book);
    }

    @Override
    public Book update(@NotNull Book book) {
        BookEntity bookEntity = bookJpaRepository.getReferenceById(book.getId());
        bookMapper.updateBookEntity(bookEntity, book, getReferenceAuthorEntities(book));
        return saveAndFlush(bookEntity, book);
    }

    @Override
    public void deleteById(Long id) {
        bookJpaRepository.deleteById(id);
    }

    private List<AuthorEntity> getReferenceAuthorEntities(@NotNull Book book) {
        return book.getAuthors().stream()
                .map(author -> entityManager.getReference(AuthorEntity.class, author.getId()))
                .toList();
    }

    private Book saveAndFlush(BookEntity bookEntity, Book book) {
        BookEntity bookEntitySaved = bookJpaRepository.save(bookEntity);
        bookMapper.updateBook(book, bookEntitySaved);
        return book;
    }
}
