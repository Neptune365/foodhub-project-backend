package com.project.FoodHub.exception;

public class UsuarioNoValidoException extends RuntimeException {
    public UsuarioNoValidoException(String message) {
        super(message);
    }
}