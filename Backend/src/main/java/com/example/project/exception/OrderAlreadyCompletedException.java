package com.example.project.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OrderAlreadyCompletedException extends Exception{
    public OrderAlreadyCompletedException(String message) {
        super(message);
    }

}
