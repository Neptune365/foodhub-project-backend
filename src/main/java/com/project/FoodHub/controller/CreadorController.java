package com.project.FoodHub.controller;

import com.project.FoodHub.dto.AuthRequest;
import com.project.FoodHub.dto.AuthResponse;
import com.project.FoodHub.dto.ModificarPerfilRequest;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.service.CreadorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creador")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200/")
public class CreadorController {

    private final CreadorService creadorService;

    @GetMapping("/todos")
    public List<Creador> mostrarCreador() {
        return creadorService.mostrarCreadores();
    }

    @GetMapping("/cantidadRecetas")
    public ResponseEntity<Integer> obtenerCantidadRecetasCreadas() {
        Integer cantidadRecetas = creadorService.obtenerCantidadDeRecetasCreadas();

        return ResponseEntity.ok(cantidadRecetas);
    }

    @PatchMapping("/perfil")
    public ResponseEntity<String> modificarPerfil(@Valid @RequestBody ModificarPerfilRequest request) {
        String nuevaFotoPerfil = request.getFotoPerfil();
        creadorService.modificarPerfil(nuevaFotoPerfil);
        return ResponseEntity.ok("Perfil modificado con éxito");
    }
}
