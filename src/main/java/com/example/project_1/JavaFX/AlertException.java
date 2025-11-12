package com.example.project_1.JavaFX;

//I use it to throw errors whenever I want something went wrong
public class AlertException extends RuntimeException {
    public AlertException(String message) {
        super(message);
    }
}
