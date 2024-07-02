package com.example.project.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CannotUseUserException extends Exception{
    public CannotUseUserException(String message) {
        super(message);
    }

}
