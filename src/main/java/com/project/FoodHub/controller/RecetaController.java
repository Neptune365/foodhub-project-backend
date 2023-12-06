package com.project.FoodHub.controller;

import com.project.FoodHub.dto.ConfirmacionResponse;
import com.project.FoodHub.dto.RecetasCategoriaResponse;
import com.project.FoodHub.dto.RecetaRequest;
import com.project.FoodHub.entity.Categoria;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.service.RecetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/explorar")
@RequiredArgsConstructor
@CrossOrigin("https://stalwart-syrniki-23893a.netlify.app")
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping("/crear")
    public ResponseEntity<ConfirmacionResponse> crearReceta(@Valid @RequestBody RecetaRequest recetaRequest) {
        return ResponseEntity.ok(recetaService.crearReceta(recetaRequest));
    }

    @GetMapping("/recetas")
    public ResponseEntity<List<RecetasCategoriaResponse>> mostrarRecetasPorCategoria(@RequestParam("categoria") String categoriaStr) {
        Categoria categoria = Categoria.fromString(categoriaStr);
        List<RecetasCategoriaResponse> listaRecetas = recetaService.mostrarRecetasPorCategoria(categoria);
        return ResponseEntity.ok(listaRecetas);
    }

    @GetMapping("/{idReceta}")
    public ResponseEntity<Receta> verReceta(@PathVariable("idReceta") Long idReceta) {
        Receta receta = recetaService.verReceta(idReceta);
        return ResponseEntity.ok(receta);
    }

}
