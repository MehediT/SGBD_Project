package org.example.exception;

public class SQLFailQuery extends RuntimeException {
    public SQLFailQuery(String message) {
        super(message);
    }
}
