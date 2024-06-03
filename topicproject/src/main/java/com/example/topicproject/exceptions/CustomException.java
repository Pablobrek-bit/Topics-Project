package com.example.topicproject.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class CustomException<T> {

    private final String description;
    private final T error;
    private final LocalDateTime timestamp;
    private final HttpStatus status;

    public CustomException(String description, T error, HttpStatus status){
        this.description = description;
        this.error = error;
        this.timestamp = LocalDateTime.now();
        this.status = status;
    }

}
