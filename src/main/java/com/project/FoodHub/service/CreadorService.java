package com.project.FoodHub.service;

import com.project.FoodHub.dto.CreadorDTO;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.repository.CreadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreadorService {

    private final CreadorRepository creadorRepository;

    public List<Creador> mostrarCreadores() {
        return creadorRepository.findAll();
    }

    public void crearCuenta(Creador creador) {
        creadorRepository.save(creador);
    }

    public void iniciarSesion(CreadorDTO creadorDTO) {
        String identificador = creadorDTO.getIdentificador();
        String contrasena = creadorDTO.getContrasena();
        Creador creador = null;

        if (identificador.contains("@")) {
            creador = creadorRepository.findByCorreoElectronico(identificador);
        } else {
            creador = creadorRepository.findByCodigoColegiatura(identificador);
        }

        if (creador != null && creador.getContrasena().equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso para " + identificador);
        } else {
            System.out.println("Inicio de sesión fallido para " + identificador);
        }
    }

    public Integer obtenerCantidadDeRecetasCreadas(Long creadorId){
        Creador creador = creadorRepository.findById(creadorId).orElse(null);

        if (creador != null) {
            List<Receta> recetasDelCreador = creador.getRecetas();
            if (recetasDelCreador != null) {
                return recetasDelCreador.size();
            }
        }

        return 0;
    }
}
