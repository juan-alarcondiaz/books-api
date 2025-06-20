package com.informacolombia.test.booksapi.authors.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AuthorDeletionException extends RuntimeException {
    public AuthorDeletionException(String message) {
        super(message);
    }
}
