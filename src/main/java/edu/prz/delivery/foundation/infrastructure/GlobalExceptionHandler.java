package edu.prz.delivery.foundation.infrastructure;

import edu.prz.delivery.foundation.exceptions.ConflictException;
import edu.prz.delivery.foundation.exceptions.ErrorResponse;
import edu.prz.delivery.foundation.exceptions.ResourceNotFoundException;
import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
    ErrorResponse error = ErrorResponse.builder()
        .message(ex.getMessage())
        .status(HttpStatus.NOT_FOUND.value())
        .timestamp(Instant.now().toString())
        .build();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(ConflictException.class)
  public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
    ErrorResponse error = ErrorResponse.builder()
        .message(ex.getMessage())
        .status(HttpStatus.CONFLICT.value())
        .timestamp(Instant.now().toString())
        .build();
    return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String validationDetails = ex.getBindingResult().getAllErrors().stream()
        .map(error -> {
          String fieldName = ((FieldError) error).getField();
          String errorMessage = error.getDefaultMessage();
          return fieldName + ": " + errorMessage;
        })
        .collect(Collectors.joining(", "));
    
    ErrorResponse error = ErrorResponse.builder()
        .message("Błąd walidacji: [" + validationDetails + "]")
        .status(HttpStatus.BAD_REQUEST.value())
        .timestamp(Instant.now().toString())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
    ErrorResponse error = ErrorResponse.builder()
        .message(ex.getMessage())
        .status(HttpStatus.BAD_REQUEST.value())
        .timestamp(Instant.now().toString())
        .build();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
  }
}
