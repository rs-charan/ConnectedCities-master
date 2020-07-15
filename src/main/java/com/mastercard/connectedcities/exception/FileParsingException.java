package com.mastercard.connectedcities.exception;

public class FileParsingException extends RuntimeException{

    public FileParsingException() {
        super();
    }

    public FileParsingException(String message, Throwable t) {
        super(message, t);
    }
}
