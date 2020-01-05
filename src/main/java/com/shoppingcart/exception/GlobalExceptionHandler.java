package com.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateKeyException.class, NotFoundException.class, AppException.class})
    public ResponseEntity handleException(Exception exception) {
        HttpStatus status;
        if (exception instanceof DuplicateKeyException) {
            status = HttpStatus.CONFLICT;
            return new ResponseEntity(status);
        }
        if (exception instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
            return new ResponseEntity(status);
        }
        if (exception instanceof AppException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            return new ResponseEntity(status);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
