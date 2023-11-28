package com.project.FoodHub.service;

import com.project.FoodHub.dto.RecetasCategoriaResponse;
import com.project.FoodHub.dto.RecetaRequest;
import com.project.FoodHub.entity.*;
import com.project.FoodHub.exception.CreadorNoEncontradoException;
import com.project.FoodHub.exception.RecetaNoEncontradaException;
import com.project.FoodHub.repository.CreadorRepository;
import com.project.FoodHub.repository.IngredienteRepository;
import com.project.FoodHub.repository.InstruccionRepository;
import com.project.FoodHub.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CreadorRepository creadorRepository;
    private final IngredienteRepository ingredienteRepository;
    private final InstruccionRepository instruccionRepository;


    @Transactional
    public void crearReceta(Long creadorId, RecetaRequest recetaRequest) {
        Creador creador = creadorRepository.findById(creadorId)
                .orElseThrow(() -> new CreadorNoEncontradoException("Creador no encontrado con ID: " + creadorId));

        Receta receta = Receta.builder()
                .titulo(recetaRequest.getTitulo())
                .descripcion(recetaRequest.getDescripcion())
                .tiempoCoccion(recetaRequest.getTiempoCoccion())
                .porciones(recetaRequest.getPorciones())
                .calorias(recetaRequest.getCalorias())
                .imagen(recetaRequest.getImagen())
                .categoria(recetaRequest.getCategoria())
                .creador(creador)
                .build();

        for (Ingrediente ingrediente : recetaRequest.getIngredientes()) {
            agregarIngrediente(receta, ingrediente);
        }

        for (Instruccion instruccion : recetaRequest.getInstrucciones()) {
            agregarInstruccion(receta, instruccion);
        }

    }

    @Transactional
    public void agregarIngrediente(Receta receta, Ingrediente ingrediente) {
        if (receta.getIngredientes() == null) {
            receta.setIngredientes(new ArrayList<>());
        }

        ingrediente.setReceta(receta);
        receta.getIngredientes().add(ingrediente);

        ingredienteRepository.save(ingrediente);
        recetaRepository.save(receta);
    }

    @Transactional
    public void agregarInstruccion(Receta receta, Instruccion instruccion) {
        if (receta.getInstrucciones() == null) {
            receta.setInstrucciones(new ArrayList<>());
        }

        instruccion.setReceta(receta);
        receta.getInstrucciones().add(instruccion);

        instruccionRepository.save(instruccion);
        recetaRepository.save(receta);
    }


    public List<RecetasCategoriaResponse> mostrarRecetasPorCategoria(Categoria categoria) {
        List<Receta> recetas = recetaRepository.findByCategoria(categoria);
        List<RecetasCategoriaResponse> recetasResponse = new ArrayList<>();

        for (Receta receta : recetas) {
            RecetasCategoriaResponse recetasCategoriaResponse = RecetasCategoriaResponse.builder()
                    .titulo(receta.getTitulo())
                    .descripcion(receta.getDescripcion())
                    .imagenReceta(receta.getImagen())
                    .build();
            recetasResponse.add(recetasCategoriaResponse);
        }

        return recetasResponse;
    }

    public Receta verReceta(Long idReceta) {
        return recetaRepository.findById(idReceta)
                .orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));
    }

}
