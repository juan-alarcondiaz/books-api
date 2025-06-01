package com.informacolombia.test.booksapi.authors.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthorDTO(@NotBlank String firstName, @NotBlank String lastName) {
}
