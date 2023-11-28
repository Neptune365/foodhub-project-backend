package com.project.FoodHub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModificarPerfilRequest {
    @NotBlank(message = "Por favor, ingresa una foto de perfil")
    private String fotoPerfil;
}
