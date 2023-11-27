package com.project.FoodHub.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    @NotBlank(message = "Por favor ingresa un correo electrónico.")
    private String identificador;

    @NotBlank(message = "Por favor ingresa una contraseña")
    private String contrasenia;
}
