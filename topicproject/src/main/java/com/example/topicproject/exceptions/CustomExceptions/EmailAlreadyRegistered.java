package com.example.topicproject.exceptions.CustomExceptions;

public class EmailAlreadyRegistered extends RuntimeException{
    public EmailAlreadyRegistered(String message) {
        super(message);
    }
}
