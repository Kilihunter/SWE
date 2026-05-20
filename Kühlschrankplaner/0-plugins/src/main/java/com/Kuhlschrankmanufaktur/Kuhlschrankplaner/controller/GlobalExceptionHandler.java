package com.Kuhlschrankmanufaktur.Kuhlschrankplaner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    
    public record FehlerAntwort(String fehlerTyp, String nachricht) {}

    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<FehlerAntwort> handleIllegalArgument(IllegalArgumentException ex) {
        
        FehlerAntwort antwort = new FehlerAntwort("Eingabefehler / Geschäftsregel verletzt", ex.getMessage());
        
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(antwort);
    }
}