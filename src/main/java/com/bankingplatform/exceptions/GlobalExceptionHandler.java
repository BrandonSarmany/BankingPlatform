package com.bankingplatform.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<String> handleInvalidAmount(InvalidAmountException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleInsufficientFunds(
            InsufficientFundsException exception) {

        return ResponseEntity.badRequest().body(exception.getMessage());
    }

    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<String> handleAccountNotFound(AccountNotFound exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidAction.class)
    public ResponseEntity<String> handleInvalidAction(InvalidAction exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}