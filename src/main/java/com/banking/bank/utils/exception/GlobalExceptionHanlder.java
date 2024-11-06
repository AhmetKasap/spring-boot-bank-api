package com.banking.bank.utils.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHanlder {

    //* CUSTOM ERROR
    @ExceptionHandler(APIError.class)
    public ResponseEntity<ErrorResponse> handleCustomError(APIError ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(), ex.getMessage());
        return ResponseEntity.status(ex.getStatusCode()).body(errorResponse);
    }

    // ALL ERROR
    @Order(2)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(500, ex.getMessage());
        //ErrorResponse errorResponse = new ErrorResponse(500, "Api Error");

        return ResponseEntity.status(500).body(errorResponse);
    }






}
