package com.project.FoodHub.service;

import com.project.FoodHub.dto.ConfirmacionResponse;
import com.project.FoodHub.dto.RecetasCategoriaResponse;
import com.project.FoodHub.dto.RecetaRequest;
import com.project.FoodHub.entity.*;
import com.project.FoodHub.exception.*;
import com.project.FoodHub.repository.CreadorRepository;
import com.project.FoodHub.repository.IngredienteRepository;
import com.project.FoodHub.repository.InstruccionRepository;
import com.project.FoodHub.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecetaService {

    private final RecetaRepository recetaRepository;
    private final CreadorRepository creadorRepository;
    private final IngredienteRepository ingredienteRepository;
    private final InstruccionRepository instruccionRepository;


    @Transactional
    public ConfirmacionResponse crearReceta(RecetaRequest recetaRequest) {
        Long idCreador = obtenerIdCreadorAutenticado();

        Creador creador = creadorRepository.findById(idCreador)
                .orElseThrow(() -> new CreadorNoEncontradoException("Creador no encontrado con ID: " + idCreador));

        Receta receta = Receta.builder()
                .titulo(recetaRequest.getTitulo())
                .descripcion(recetaRequest.getDescripcion())
                .tiempoCoccion(recetaRequest.getTiempoCoccion())
                .porciones(recetaRequest.getPorciones())
                .calorias(recetaRequest.getCalorias())
                .imagen(recetaRequest.getImagen())
                .categoria(recetaRequest.getCategoria())
                .ingredientes(new ArrayList<>())
                .instrucciones(new ArrayList<>())
                .creador(creador)
                .build();

        recetaRepository.save(receta);

        for (Ingrediente ingrediente : recetaRequest.getIngredientes()) {
            agregarIngrediente(receta, ingrediente);
        }

        for (Instruccion instruccion : recetaRequest.getInstrucciones()) {
            agregarInstruccion(receta, instruccion);
        }

        return new ConfirmacionResponse("Receta creada de forma exitosa", "success");
    }

    @Transactional
    public void agregarIngrediente(Receta receta, Ingrediente ingrediente) {
        ingrediente.setReceta(receta);
        receta.getIngredientes().add(ingrediente);

        ingredienteRepository.save(ingrediente);
    }

    @Transactional
    public void agregarInstruccion(Receta receta, Instruccion instruccion) {
        instruccion.setReceta(receta);
        receta.getInstrucciones().add(instruccion);

        instruccionRepository.save(instruccion);
    }

    @Transactional
    public List<RecetasCategoriaResponse> mostrarRecetasPorCategoria(Categoria categoria) {
        Optional<List<Receta>> recetasOptional = recetaRepository.findByCategoria(categoria);

        List<RecetasCategoriaResponse> recetasResponse = new ArrayList<>();

        List<Receta> recetas = recetasOptional.orElseThrow(() -> new ListaRecetasNulaException("La lista de recetas es nula"));

        for (Receta receta : recetas) {
            RecetasCategoriaResponse recetasCategoriaResponse = RecetasCategoriaResponse.builder()
                    .id(receta.getId())
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

    private Long obtenerIdCreadorAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        validarAutenticacion(authentication);
        return ((Creador) authentication.getPrincipal()).getIdCreador();
    }

    private void validarAutenticacion(Authentication authentication) {
        Optional.ofNullable(authentication)
                .filter(Authentication::isAuthenticated)
                .orElseThrow(() -> new UsuarioNoAutenticadoException("Usuario no autenticado"));
    }

}
