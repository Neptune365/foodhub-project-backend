package com.project.FoodHub.exception;

public class CuentaNoConfirmadaException extends RuntimeException {
    public CuentaNoConfirmadaException(String mensaje) {
        super(mensaje);
    }
}