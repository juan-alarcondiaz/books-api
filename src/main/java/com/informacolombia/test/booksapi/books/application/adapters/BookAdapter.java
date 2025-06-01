package com.informacolombia.test.booksapi.books.application.adapters;

import com.informacolombia.test.booksapi.books.application.mappers.BookUpdaterMapper;
import com.informacolombia.test.booksapi.books.application.ports.input.BookPort;
import com.informacolombia.test.booksapi.books.application.ports.output.BookRepository;
import com.informacolombia.test.booksapi.books.domain.exception.BookNotFoundException;
import com.informacolombia.test.booksapi.books.domain.models.Book;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookAdapter implements BookPort {

    private final BookRepository bookRepository;
    private final BookUpdaterMapper bookUpdaterManager;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    @Override
    public Book create(Book book) {
        return bookRepository.create(book);
    }

    @Override
    public Book update(@NotNull Book book, Long id) {
        try {
            Book existingBook = this.getById(id);
            bookUpdaterManager.updateBook(existingBook, book);
            return bookRepository.update(existingBook);
        } catch (BookNotFoundException e) {
            throw new BookNotFoundException("Cannot update: Book with ID " + id + " does not exist.");
        }
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Cannot delete: Book with ID " + id + " does not exist.");
        }
        bookRepository.deleteById(id);
    }
}
