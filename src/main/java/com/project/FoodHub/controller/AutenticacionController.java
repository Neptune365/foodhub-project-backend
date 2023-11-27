package com.project.FoodHub.controller;

import com.project.FoodHub.dto.CreadorRequest;
import com.project.FoodHub.registration.RegistroService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AutenticacionController {

    private final RegistroService registroService;

    @PostMapping("/registrar")
    public ResponseEntity<?> register(@Valid @RequestBody CreadorRequest request/*, HttpServletRequest httpServletRequest*/){
        /*httpServletRequest.getHeader(HttpHeaders.ORIGIN);*/
        return ResponseEntity.ok(registroService.registrar(request));
    }
}
