package com.company;

public class InvalidOperationException extends Exception {
    public InvalidOperationException(String message) {
        super(message);
    }

    InvalidOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
