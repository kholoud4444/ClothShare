package com.ntg.backend.exception;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "RESOURCE_NOT_FOUND",
                null
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> handleValidationException(MethodArgumentNotValidException exception,
                                                                  WebRequest webRequest) {
        Map<String, String> validationErrors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(error.getField(), error.getDefaultMessage());
        }

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Validation error",
                webRequest.getDescription(false),
                "VALIDATION_ERROR",
                validationErrors
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleDataIntegrityViolationException(DataIntegrityViolationException exception,
                                                                              WebRequest webRequest) {
        String constraintMessage = extractConstraintDetail(exception.getMessage());
        Map<String, String> constraintDetails = new HashMap<>();
        constraintDetails.put("constraintViolation", constraintMessage);

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Unique constraint violation",
                webRequest.getDescription(false),
                "DATA_INTEGRITY_VIOLATION",
                constraintDetails
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDetails> handleConstraintViolationException(
            ConstraintViolationException exception, WebRequest webRequest) {

        Map<String, String> fieldErrors = new HashMap<>();
        for (ConstraintViolation<?> violation : exception.getConstraintViolations()) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            fieldErrors.put(field, message);
        }

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                "Validation failed",
                webRequest.getDescription(false),
                "CONSTRAINT_VIOLATION",
                fieldErrors
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {

        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL_SERVER_ERROR",
                null
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String extractConstraintDetail(String message) {
        if (message.contains("Key") && message.contains("already exists")) {
            return message.substring(message.indexOf("Key"), message.indexOf("already exists") + "already exists".length());
        }
        return "Constraint violation detected";
    }
}
