package com.project.FoodHub.service;

import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.repository.CreadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreadorService implements UsuarioService {

    private final CreadorRepository creadorRepository;

    public String crearCuenta(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String contraseña, String codigoColegiatura) {
        return "";
    }

    @Override
    public Receta verReceta(Receta receta) {
        return null;
    }
}
