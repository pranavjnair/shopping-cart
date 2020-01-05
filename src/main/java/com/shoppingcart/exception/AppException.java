package com.shoppingcart.exception;

public class AppException extends RuntimeException {

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable exception) {
        super(message, exception);
    }
}