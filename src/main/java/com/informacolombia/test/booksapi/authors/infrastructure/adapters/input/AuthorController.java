package com.informacolombia.test.booksapi.authors.infrastructure.adapters.input;

import com.informacolombia.test.booksapi.authors.application.ports.input.AuthorPort;
import com.informacolombia.test.booksapi.authors.domain.models.Author;
import com.informacolombia.test.booksapi.authors.infrastructure.dto.AuthorDTO;
import com.informacolombia.test.booksapi.authors.infrastructure.mappers.AuthorRequestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("authors")
class AuthorController {

    private final AuthorPort authorPort;
    private final AuthorRequestMapper authorRequestMapper;

    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(authorPort.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<Author> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(authorPort.getById(id));
    }

    @PostMapping
    public ResponseEntity<Author> create(@Valid @RequestBody AuthorDTO authorDTO) {
        Author author = authorPort.create(authorRequestMapper.toAuthor(authorDTO));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getId())
                .toUri();
        return ResponseEntity.created(location).body(author);
    }

    @PutMapping("{id}")
    public ResponseEntity<Author> update(@PathVariable("id") Long id, @Valid @RequestBody AuthorDTO authorDTO) {
        Author author = authorPort.update(authorRequestMapper.toAuthor(authorDTO), id);
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        authorPort.delete(id);
        return ResponseEntity.noContent().build();
    }
}
