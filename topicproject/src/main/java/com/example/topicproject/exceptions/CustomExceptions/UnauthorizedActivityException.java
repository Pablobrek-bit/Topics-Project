package com.example.topicproject.exceptions.CustomExceptions;

public class UnauthorizedActivityException extends RuntimeException{
    public UnauthorizedActivityException(String message) {
        super(message);
    }
}
