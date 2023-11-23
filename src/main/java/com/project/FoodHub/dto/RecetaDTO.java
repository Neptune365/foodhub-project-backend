package com.project.FoodHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecetaDTO {
    private String titulo;
    private String descripcion;
    private String imagenReceta;
}
