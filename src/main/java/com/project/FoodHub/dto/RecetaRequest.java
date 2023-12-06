package com.project.FoodHub.dto;

import com.project.FoodHub.entity.Categoria;
import com.project.FoodHub.entity.Ingrediente;
import com.project.FoodHub.entity.Instruccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaRequest {

    @NotBlank(message = "El título no puede estar en blanco")
    @Size(max = 255, message = "El título no puede tener más de 255 caracteres")
    private String titulo;

    @NotBlank(message = "La descripción no puede estar en blanco")
    @Size(max = 1000, message = "La descripción no puede tener más de 1000 caracteres")
    private String descripcion;

    @NotNull(message = "El tiempo de cocción no puede ser nulo")
    @Positive(message = "El tiempo de cocción debe ser un número positivo")
    private Integer tiempoCoccion;

    @NotNull(message = "El número de porciones no puede ser nulo")
    @Positive(message = "El número de porciones debe ser un número positivo")
    private Integer porciones;

    @NotNull(message = "Las calorías no pueden ser nulas")
    @Positive(message = "Las calorías deben ser un número positivo")
    private Double calorias;

    @NotBlank(message = "La URL de la imagen no puede estar en blanco")
    private String imagen;

    @NotNull(message = "La categoría no puede ser nula")
    private Categoria categoria;

    @NotNull(message = "La lista de ingredientes no puede ser nula")
    @Size(min = 1, message = "Debe haber al menos un ingrediente")
    private List<@Valid Ingrediente> ingredientes;

    @NotNull(message = "La lista de instrucciones no puede ser nula")
    @Size(min = 1, message = "Debe haber al menos una instrucción")
    private List<@Valid Instruccion> instrucciones;
}
