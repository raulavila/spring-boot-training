package io.pivotal.training.controller;

import io.pivotal.training.errors.AppError;
import io.pivotal.training.errors.NutNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NutNotFoundException.class)
    public ResponseEntity<AppError> nutNotFound(NutNotFoundException nutNotFoundException) {
        AppError error = new AppError(nutNotFoundException.getMessage());

        ResponseEntity<AppError> responseEntity =
                new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}
