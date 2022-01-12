package com.example.coffees.exceptions;

public class NoElementFoundException extends RuntimeException {
    public NoElementFoundException(String message) {
        super(message);
    }
}
