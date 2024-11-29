package org.example.exception;

public class SQLFailUpdate extends RuntimeException {
    public SQLFailUpdate(String message) {
        super(message);
    }
}

