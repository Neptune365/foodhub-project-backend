package com.project.FoodHub.service;

import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.repository.CreadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreadorService implements UsuarioService {

    private final CreadorRepository creadorRepository;

    public List<Creador> mostrarCreador() {
        return creadorRepository.findAll();
    }


//    POR REVISAR
    public void crearCuenta(Creador creador) {
        creadorRepository.save(creador);
    }
    public String crearCuenta(String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, String contraseña, String codigoColegiatura) {
        return "";
    }
//



//  POR DESARROLLAR
    public Creador modificarPerfil(Creador creador) {
        return null;
    }

    @Override
    public Receta verReceta(Receta receta) {
        return null;
    }

}
