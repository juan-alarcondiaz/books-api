package com.informacolombia.test.booksapi.books.infrastructure.adapters.input;

import com.informacolombia.test.booksapi.books.application.ports.input.BookPort;
import com.informacolombia.test.booksapi.books.domain.models.Book;
import com.informacolombia.test.booksapi.books.infrastructure.dto.BookDTO;
import com.informacolombia.test.booksapi.books.infrastructure.mappers.BookRequestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("books")
class BookController {

    private final BookPort bookPort;
    private final BookRequestMapper bookRequestMapper;

    @GetMapping
    public ResponseEntity<List<Book>> getAll() {
        return ResponseEntity.ok(bookPort.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Book> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bookPort.getById(id));
    }

    @PostMapping
    public ResponseEntity<Book> create(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookPort.create(bookRequestMapper.toBook(bookDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getId())
                .toUri();
        return ResponseEntity.created(location).body(book);
    }

    @PutMapping("{id}")
    public ResponseEntity<Book> update(@PathVariable("id") Long id, @Valid @RequestBody BookDTO bookDTO) {
        Book book = bookPort.update(bookRequestMapper.toBook(bookDTO), id);
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        bookPort.delete(id);
        return ResponseEntity.noContent().build();
    }

}
