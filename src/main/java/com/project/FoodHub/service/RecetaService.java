package com.project.FoodHub.service;

import com.project.FoodHub.dto.RecetaDTO;
import com.project.FoodHub.dto.RecetaDTORequest;
import com.project.FoodHub.entity.*;
import com.project.FoodHub.repository.CreadorRepository;
import com.project.FoodHub.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CreadorRepository creadorRepository;


    public void crearReceta(Long creadorId, RecetaDTORequest recetaDTORequest) {
        Creador creador = creadorRepository.findById(creadorId)
                .orElseThrow(() -> new RuntimeException("Creador no encontrado con ID: " + creadorId));

        Receta receta = recetaDTORequest.getReceta();
        Categoria categoria = recetaDTORequest.getCategoria();
        List<Ingrediente> ingredientes = recetaDTORequest.getIngredientes();
        List<Instruccion> instrucciones = recetaDTORequest.getInstrucciones();

        if (categoria != null) {
            receta.setCategoria(categoria);
        } else {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }

        receta.setCreador(creador);

        for (Ingrediente ingrediente : ingredientes) {
            receta.añadirIngrediente(ingrediente);
        }

        for (Instruccion instruccion : instrucciones) {
            receta.añadirInstruccion(instruccion);
        }

        recetaRepository.save(receta);
    }


    public List<RecetaDTO> mostrarRecetasPorCategoria(Categoria categoria) {
        List<Receta> recetas = recetaRepository.findByCategoria(categoria);
        List<RecetaDTO> recetasDTO = new ArrayList<>();

        for (Receta receta : recetas) {
            RecetaDTO recetaDTO = RecetaDTO.builder()
                    .titulo(receta.getTitulo())
                    .descripcion(receta.getDescripcion())
                    .imagenReceta(receta.getImagen())
                    .build();
            recetasDTO.add(recetaDTO);
        }

        return recetasDTO;
    }

    public Receta verReceta(Long creadorId) {
        return recetaRepository.findById(creadorId)
                .orElseThrow(() -> new RuntimeException("Receta no encontrada con ID: " + creadorId));
    }

}
