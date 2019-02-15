package com.epam.lab.exception;

public class NoSuchMessageFoundException extends RuntimeException {

    public NoSuchMessageFoundException(String message) {
        super(message);
    }
}
