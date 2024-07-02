package com.example.project.exception;

public class UserIncorrectPasswordException extends Exception{
    public UserIncorrectPasswordException(String message) {
        super(message);
    }
}
