package com.project.FoodHub.controller;

import com.project.FoodHub.dto.CreadorDTO;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.service.CreadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creador")
@RequiredArgsConstructor
public class CreadorController {

    private final CreadorService creadorService;

    @GetMapping("/todos")
    public List<Creador> mostrarCreador() {
        return creadorService.mostrarCreadores();
    }

    @PostMapping("/registro")
    public ResponseEntity<String> crearCuenta(@RequestBody Creador creador) {
        creadorService.crearCuenta(creador);
        return ResponseEntity.ok("Creador creado exitosamente");
    }

    @PostMapping("/logeo")
    public ResponseEntity<String> iniciarSesion(@RequestBody CreadorDTO creadorDTO) {
        creadorService.iniciarSesion(creadorDTO);
        return ResponseEntity.ok("Ha iniciado sesión");
    }

    @GetMapping("/{creadorId}/cantidadRecetas")
    public ResponseEntity<Integer> obtenerCantidadRecetasCreadas(@PathVariable Long creadorId) {
        Integer cantidadRecetas = creadorService.obtenerCantidadDeRecetasCreadas(creadorId);

        return ResponseEntity.ok(cantidadRecetas);
    }
}
