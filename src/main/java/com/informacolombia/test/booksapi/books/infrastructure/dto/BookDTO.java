package com.informacolombia.test.booksapi.books.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public record BookDTO(@NotBlank String title, @NotNull LocalDate publishDate, @NotEmpty Set<Long> authors) {
}