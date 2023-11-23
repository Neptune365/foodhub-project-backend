package com.project.FoodHub.service;

import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.repository.CreadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreadorService {

    private final CreadorRepository creadorRepository;

    public List<Creador> mostrarCreador() {
        return creadorRepository.findAll();
    }

    public void crearCuenta(Creador creador) {
        creadorRepository.save(creador);
    }
}
