package com.example.topicproject.exceptions.CustomExceptions;

public class ResponseNotFoundException extends RuntimeException{
    public ResponseNotFoundException(String message) {
        super(message);
    }
}
