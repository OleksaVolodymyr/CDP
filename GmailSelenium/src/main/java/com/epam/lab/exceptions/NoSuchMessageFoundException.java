package com.epam.lab.exceptions;

public class NoSuchMessageFoundException extends RuntimeException {

    public NoSuchMessageFoundException(String message) {
        super(message);
    }
}
