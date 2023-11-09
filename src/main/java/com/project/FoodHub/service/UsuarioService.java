package com.project.FoodHub.service;

import com.project.FoodHub.entity.Receta;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioService {

    Receta verReceta(Receta receta);

}
