package com.project.FoodHub.controller;

import com.project.FoodHub.dto.RecetasCategoriaResponse;
import com.project.FoodHub.dto.RecetaRequest;
import com.project.FoodHub.entity.Categoria;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/explorar")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearReceta(
            @RequestParam Long creadorId,
            @RequestBody RecetaRequest recetaRequest) {
        recetaService.crearReceta(creadorId, recetaRequest);
        return new ResponseEntity<>("Receta creada exitosamente", HttpStatus.CREATED);
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<RecetasCategoriaResponse>> mostrarRecetasPorCategoria(@RequestParam Categoria categoria) {
        List<RecetasCategoriaResponse> recetasDTO = recetaService.mostrarRecetasPorCategoria(categoria);
        return ResponseEntity.ok(recetasDTO);
    }

    @GetMapping("/receta")
    public ResponseEntity<Receta> verReceta(@RequestParam Long idReceta) {
        Receta receta = recetaService.verReceta(idReceta);
        return ResponseEntity.ok(receta);
    }

}
