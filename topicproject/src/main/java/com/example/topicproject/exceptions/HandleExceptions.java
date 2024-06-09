package com.example.topicproject.exceptions;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.topicproject.domain.dto.error.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@ControllerAdvice
public class HandleExceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request){
        List<ErrorDTO> error = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> new ErrorDTO(err.getField(), err.getDefaultMessage()))
                .toList();

        var errorResponse =
                new CustomException<>(request.getDescription(false),
                        error, HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<?> handleTokenExpiredException(TokenExpiredException ex, WebRequest request){
        var errorResponse =
                new CustomException<>(request.getDescription(false),
                        ex.getMessage(), HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, WebRequest request){
        var errorResponse =
                new CustomException<>(request.getDescription(false),
                        ex.getMessage(), HttpStatus.CONFLICT);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex, WebRequest request){
        var errorResponse =
                new CustomException<>(request.getDescription(false),
                        ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

}
