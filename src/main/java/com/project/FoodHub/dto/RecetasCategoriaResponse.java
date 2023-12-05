package com.project.FoodHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetasCategoriaResponse {
    private Long id;
    private String titulo;
    private String descripcion;
    private String imagenReceta;
}
