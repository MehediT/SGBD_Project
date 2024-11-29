package org.example.exception;

public class SQLConnectionException extends RuntimeException {
    public SQLConnectionException(String message) {
        super(message);
    }
}
