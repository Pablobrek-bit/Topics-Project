package com.example.topicproject.exceptions.CustomExceptions;

public class TopicNotFoundException extends RuntimeException{
    public TopicNotFoundException(String message) {
        super(message);
    }
}
