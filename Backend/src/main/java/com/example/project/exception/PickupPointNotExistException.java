package com.example.project.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PickupPointNotExistException extends Exception{
    public PickupPointNotExistException(String message) {
        super(message);
    }
}
