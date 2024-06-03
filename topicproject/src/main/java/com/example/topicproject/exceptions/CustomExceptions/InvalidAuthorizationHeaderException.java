package com.example.topicproject.exceptions.CustomExceptions;

public class InvalidAuthorizationHeaderException extends RuntimeException {
    public InvalidAuthorizationHeaderException(String invalidAuthorizationHeader) {
        super(invalidAuthorizationHeader);
    }
}
