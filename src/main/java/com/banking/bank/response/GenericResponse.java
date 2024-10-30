package com.banking.bank.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenericResponse<T> {

    private boolean success;
    private String statusCode;
    private String message;
    private T data;


    public static <T> GenericResponse<T> ok(T data, String message) {
        return new GenericResponse<>(true, "200", message, data);
    }

    public static <T> GenericResponse<T> created(T data, String message) {
        return new GenericResponse<>(true, "201", message, data);
    }

    public static <T> GenericResponse<T> notFound(String message) {
        return new GenericResponse<>(false, "404", message, null);
    }

    public static <T> GenericResponse<T> badRequest(T data, String message) {
        return new GenericResponse<>(false, "400", message != null ? message : "Bad request", data);
    }

    public static <T> GenericResponse<T> unauthorized(T data, String message) {
        return new GenericResponse<>(false, "401", message != null ? message : "Unauthorized transaction", data);
    }

    public static <T> GenericResponse<T> forbidden(T data, String message) {
        return new GenericResponse<>(false, "403", message != null ? message : "Forbidden", data);
    }

    public static <T> GenericResponse<T> notFound(T data, String message) {
        return new GenericResponse<>(false, "404", message != null ? message : "Not found", data);
    }

    public static <T> GenericResponse<T> conflict(T data, String message) {
        return new GenericResponse<>(false, "409", message != null ? message : "Conflict", data);
    }

    public static <T> GenericResponse<T> tooManyRequest(T data, String message) {
        return new GenericResponse<>(false, "429", message != null ? message : "Too many requests", data);
    }

    public static <T> GenericResponse<T> internalServerError(T data, String message) {
        return new GenericResponse<>(false, "500", message != null ? message : "Internal server error", data);
    }

    public static <T> GenericResponse<T> notImplemented(T data, String message) {
        return new GenericResponse<>(false, "501", message != null ? message : "Not implemented", data);
    }

    public static <T> GenericResponse<T> serviceUnavailable(T data, String message) {
        return new GenericResponse<>(false, "503", message != null ? message : "Service unavailable", data);
    }


}
