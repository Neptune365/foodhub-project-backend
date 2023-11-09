package com.project.FoodHub.service;

import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.excepcion.RecetaNotFoundException;
import com.project.FoodHub.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;

    public void crearReceta(Receta receta) {
        recetaRepository.save(receta);
    }

//  POR DESARROLLAR
    public String mostrarRecetasPorCategoria(String categoria, String titulo, String imagen, String descripcion) {
        return "";
    }


    //  POR DESARROLLAR
    public Receta verDetalleDeReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElse(null);

        if (receta == null) {
            throw new RecetaNotFoundException("LA RECETA MAMAWEBA NO EXISTE");
        }

        return receta;
    }

}
