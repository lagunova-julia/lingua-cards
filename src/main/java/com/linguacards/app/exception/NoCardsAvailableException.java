package com.linguacards.app.exception;

public class NoCardsAvailableException extends RuntimeException {
    public NoCardsAvailableException(String message) {
        super(message);
    }
}
