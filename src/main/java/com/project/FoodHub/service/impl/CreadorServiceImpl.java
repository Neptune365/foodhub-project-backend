package com.project.FoodHub.service.impl;

import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.repository.CreadorRepository;
import com.project.FoodHub.service.CreadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreadorServiceImpl implements CreadorService {

    private final CreadorRepository creadorRepository;

    @Override
    public List<Creador> mostrarCreador() {
        return null;
    }

    @Override
    public Receta verReceta(Long creadorId) {
        return null;
    }

    @Override
    public void crearCuenta(Creador creador) {

    }

    @Override
    public Creador modificarPerfil(Long creadorId, Creador creador) {
        return null;
    }
}
