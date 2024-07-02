package com.example.project.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DeliveredProductsNotExistException extends Exception{
    public DeliveredProductsNotExistException(String message) {
        super(message);
    }

}
