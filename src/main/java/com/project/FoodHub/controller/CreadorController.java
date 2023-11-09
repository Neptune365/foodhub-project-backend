package com.project.FoodHub.controller;

import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.service.CreadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/creador")
@RequiredArgsConstructor
public class CreadorController {

    private final CreadorService creadorService;

    @GetMapping("/")
    public List<Creador> mostrarCreadores() {
        return creadorService.mostrarCreador();
    }

    @PostMapping("/")
    public Creador crearCreador(@RequestBody Creador creador) {
        creadorService.crearCuenta(creador);
        return creador;
    }

}
