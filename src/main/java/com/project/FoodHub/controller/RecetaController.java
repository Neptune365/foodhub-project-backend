package com.project.FoodHub.controller;

import com.project.FoodHub.dto.RecetaDTO;
import com.project.FoodHub.entity.Categoria;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.service.RecetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receta")
@RequiredArgsConstructor
public class RecetaController {

    private final RecetaService recetaService;

    @PostMapping("/crear")
    public ResponseEntity<String> crearReceta(@RequestParam Long creadorId,
                                              @RequestBody Receta receta,
                                              @RequestParam Categoria categoria) {
        recetaService.crearReceta(creadorId, receta, categoria);
        return ResponseEntity.ok("Receta creada correctamente");
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<RecetaDTO>> mostrarRecetasPorCategoria(@RequestParam Categoria categoria) {
        List<RecetaDTO> recetasDTO = recetaService.mostrarRecetasPorCategoria(categoria);
        return ResponseEntity.ok(recetasDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receta> verReceta(@PathVariable Long id) {
        Receta receta = recetaService.verReceta(id);
        return ResponseEntity.ok(receta);
    }

}
