package com.example.demo.error;

import com.example.demo.error.exception.SomethingWentWrongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(SomethingWentWrongException.class)
    public ResponseEntity<ApiError> handleConflictException(SomethingWentWrongException ex) {
        ApiError response = ApiError.builder()
                .status(HttpStatus.NO_CONTENT.name())
                .reason("Что-то пошло не так")
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
