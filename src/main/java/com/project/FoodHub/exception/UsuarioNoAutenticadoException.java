package com.project.FoodHub.exception;

public class UsuarioNoAutenticadoException extends RuntimeException {
    public UsuarioNoAutenticadoException(String message) {
        super(message);
    }
}