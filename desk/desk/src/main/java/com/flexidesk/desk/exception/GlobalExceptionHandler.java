package com.flexidesk.desk.exception;

import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OptimisticLockingFailureException.class)
    public ResponseEntity<String> handleOptimisticLocking(
            OptimisticLockingFailureException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body("Desk was modified by another user. Please try again.");
    }
}