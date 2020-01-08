package com.shoppingcart.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DuplicateKeyException.class, NotFoundException.class, AppException.class})
    public ResponseEntity handleException(Exception exception) {
        Map<String, String> response = new HashMap<>();
        HttpStatus status;
        if (exception instanceof DuplicateKeyException) {
            status = HttpStatus.CONFLICT;
            response.put("message", "Duplicate item");
            return new ResponseEntity(response, status);
        }
        if (exception instanceof NotFoundException) {
            status = HttpStatus.NOT_FOUND;
            response.put("message", "User not found");
            return new ResponseEntity(response, status);
        }
        if (exception instanceof AppException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.put("message", "Unknown error occured");
            return new ResponseEntity(response, status);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
