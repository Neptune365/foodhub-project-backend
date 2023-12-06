package com.project.FoodHub.entity;

import com.project.FoodHub.exception.CategoriaNoValidaException;

import java.util.Arrays;

public enum Categoria {
    DESAYUNO,
    ALMUERZO,
    CENA,
    POSTRES,
    SUPERAVIT,
    DEFICIT;

    public static Categoria fromString(String categoriaStr) {
        return Arrays.stream(Categoria.values())
                .filter(c -> c.name().equalsIgnoreCase(categoriaStr))
                .findFirst()
                .orElseThrow(() -> new CategoriaNoValidaException("Categoría no válida: " + categoriaStr));
    }
}
