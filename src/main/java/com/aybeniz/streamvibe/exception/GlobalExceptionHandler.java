package com.aybeniz.streamvibe.exception;

import com.aybeniz.streamvibe.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<Object>> handleResponseStatusException(ResponseStatusException ex) {
        String message = ex.getReason() == null ? "Unexpected error" : ex.getReason();

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(ApiResponse.error(message));
    }
}