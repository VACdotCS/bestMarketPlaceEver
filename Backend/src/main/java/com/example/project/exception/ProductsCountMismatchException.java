package com.example.project.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProductsCountMismatchException extends Exception {
    public ProductsCountMismatchException(String message) {
        super(message);
    }
}
