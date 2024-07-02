package com.example.project.exception;

public class ProductNotExistException extends Exception {
    public ProductNotExistException(String message) {
        super(message);
    }
}
