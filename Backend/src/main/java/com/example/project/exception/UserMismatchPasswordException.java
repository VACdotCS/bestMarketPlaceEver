package com.example.project.exception;

public class UserMismatchPasswordException extends Exception{
    public UserMismatchPasswordException(String message) {
        super(message);
    }
}
