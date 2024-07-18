package com.express.cards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CardNotExistException.class)
    public ErrorResponseDto handleException(CardNotExistException ex) {
        return new ErrorResponseDto(HttpStatus.BAD_REQUEST,ex.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(Exception.class)
    public ErrorResponseDto handleException(Exception ex) {
        return new ErrorResponseDto(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage(), LocalDateTime.now());
    }
}
