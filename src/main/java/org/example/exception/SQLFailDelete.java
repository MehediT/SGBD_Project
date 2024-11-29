package org.example.exception;

public class SQLFailDelete extends RuntimeException {
    public SQLFailDelete(String message) {
        super(message);
    }
}
