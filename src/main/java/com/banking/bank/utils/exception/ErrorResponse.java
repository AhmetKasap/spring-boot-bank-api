package com.banking.bank.utils.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class ErrorResponse {
    private String success = "false";
    private String message;
    private int statusCode;
    private Map<String, String> errors;

    public ErrorResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(int statusCode, String message, Map<String, String> errors) {
        this.statusCode = statusCode;
        this.message = message;
        this.errors = errors;
    }
}
