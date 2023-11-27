package com.project.FoodHub.controller;

import com.project.FoodHub.dto.AuthRequest;
import com.project.FoodHub.dto.AuthResponse;
import com.project.FoodHub.dto.CreadorRequest;
import com.project.FoodHub.registration.RegistroService;
import com.project.FoodHub.service.CreadorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AutenticacionController {

    private final RegistroService registroService;
    private final CreadorService creadorService;

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@Valid @RequestBody CreadorRequest request/*, HttpServletRequest httpServletRequest*/){
        /*httpServletRequest.getHeader(HttpHeaders.ORIGIN);*/
        return ResponseEntity.ok(registroService.registrar(request));
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registroService.confirmarToken(token);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> iniciarSesion(@Valid @RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(creadorService.iniciarSesion(authRequest));
    }

}
