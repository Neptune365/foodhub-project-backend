package com.project.FoodHub.dto;

import com.project.FoodHub.entity.Categoria;
import com.project.FoodHub.entity.Ingrediente;
import com.project.FoodHub.entity.Instruccion;
import com.project.FoodHub.entity.Receta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecetaRequest {
    private String titulo;
    private String descripcion;
    private Integer tiempoCoccion;
    private Integer porciones;
    private Double calorias;
    private String imagen;
    private Categoria categoria;
    private List<Ingrediente> ingredientes;
    private List<Instruccion> instrucciones;
}
