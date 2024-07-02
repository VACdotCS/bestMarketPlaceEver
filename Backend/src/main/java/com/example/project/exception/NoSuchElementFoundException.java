package com.example.project.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchElementFoundException extends Exception {
    public NoSuchElementFoundException(String message) {
        super(message);
    }
}
