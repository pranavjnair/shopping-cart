package com.shoppingcart.exception;

public class DuplicateKeyException extends RuntimeException {

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(String message, Throwable exception) {
        super(message, exception);
    }
}