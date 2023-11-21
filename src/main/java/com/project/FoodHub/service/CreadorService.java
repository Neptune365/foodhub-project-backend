package com.project.FoodHub.service;

import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Receta;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CreadorService {

    List<Creador> mostrarCreador();

    Receta verReceta(Long creadorId);

    void crearCuenta(Creador creador);

    Creador modificarPerfil(Long creadorId, Creador creador);

}
