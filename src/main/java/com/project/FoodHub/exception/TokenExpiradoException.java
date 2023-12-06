package com.project.FoodHub.exception;

public class TokenExpiradoException extends RuntimeException {
    public TokenExpiradoException(String message) {
        super(message);
    }
}