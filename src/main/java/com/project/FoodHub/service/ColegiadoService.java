package com.project.FoodHub.service;

import com.project.FoodHub.entity.Colegiado;
import com.project.FoodHub.repository.ColegiadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColegiadoService {

    private final ColegiadoRepository colegiadoRepository;

//    POR DESARROLLAR
    public String validarColegiado(String nombreColegiado, String apellidoPaternoColegiado, String apellidoMaternoColegiado, String codigoColegiado, Colegiado colegiado) {
        return "";
    }
}
