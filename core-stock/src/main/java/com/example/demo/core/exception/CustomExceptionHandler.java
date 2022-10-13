package com.example.demo.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(StockException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ResponseEntity<Object> handleCustomException(StockException e) {
        StockExceptionMessage stockExceptionMessage = new StockExceptionMessage(StockExceptionType.STOCK_LIMIT_REACHED.getDescription());
        return new ResponseEntity<>(stockExceptionMessage, HttpStatus.NOT_ACCEPTABLE);
    }

}