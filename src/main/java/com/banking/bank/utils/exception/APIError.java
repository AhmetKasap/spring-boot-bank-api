package com.banking.bank.utils.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIError extends RuntimeException {
    private final String message;
    private final int statusCode;

    public APIError(int statusCode, String message ) {
        this.statusCode = statusCode;
        this.message = message;

    }

}
