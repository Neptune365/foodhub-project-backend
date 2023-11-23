package com.project.FoodHub.controller;

import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.service.CreadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/creador")
@RequiredArgsConstructor
public class CreadorController {

    private final CreadorService creadorService;

    @GetMapping("/todos")
    public List<Creador> mostrarCreador() {
        return creadorService.mostrarCreador();
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearCuenta(@RequestBody Creador creador) {
        creadorService.crearCuenta(creador);
        return ResponseEntity.ok("Creador creado exitosamente");
    }

//    @PutMapping("/{creadorId}/modificar-foto")
//    public ResponseEntity<Creador> modificarFotoDePerfil(@PathVariable Long creadorId, @RequestParam String foto) {
//        Creador creadorModificado = creadorService.modificarFotoDePerfil(creadorId, foto);
//        return ResponseEntity.ok(creadorModificado);
//    }

}
