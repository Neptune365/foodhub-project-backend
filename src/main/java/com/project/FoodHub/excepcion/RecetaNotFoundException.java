package com.project.FoodHub.excepcion;

public class RecetaNotFoundException extends RuntimeException {

    public RecetaNotFoundException(String mensaje) {
        super(mensaje);
    }

}
