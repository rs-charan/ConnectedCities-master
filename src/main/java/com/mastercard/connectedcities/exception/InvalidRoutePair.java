package com.mastercard.connectedcities.exception;

public class InvalidRoutePair extends RuntimeException{

    public InvalidRoutePair() {
        super();
    }

    public InvalidRoutePair(String message) {
        super(message);
    }
}
