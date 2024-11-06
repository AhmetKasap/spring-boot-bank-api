package com.banking.bank.utils.exception;

import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Order(1)
@RestControllerAdvice
public class SpesificExceptionHandler {

    //DTO VALIDATIONS
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ErrorResponse errorResponse = new ErrorResponse(400, "Validation Error", errors);
        return ResponseEntity.status(400).body(errorResponse);
    }

    // ENTITIY VALIDATIONS
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        //ErrorResponse errorResponse = new ErrorResponse(400, "Data Integrity Violation", Collections.singletonMap("error", ex.getMessage()));
        ErrorResponse errorResponse = new ErrorResponse(400, "Data Integrity Violation");

        return ResponseEntity.status(400).body(errorResponse);
    }
}
