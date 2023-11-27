package com.project.FoodHub.service;

import com.project.FoodHub.entity.Colegiado;
import com.project.FoodHub.repository.ColegiadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColegiadoService {

    private final ColegiadoRepository colegiadoRepository;

//    POR DESARROLLAR
    public boolean validarColegiado(String nombre, String apellidoPaterno, String apellidoMaterno, String codigoColegiado) {
        Colegiado colegiado = colegiadoRepository.findByNombreColegiadoAndApellidoPaternoColegiadoAndAndAppellidoMaternoColegiadoAndCodigoColegiado(
            nombre, apellidoPaterno, apellidoMaterno, codigoColegiado);
        return colegiado != null;
    }

    @Transactional
    public boolean isCuentaConfirmada(String codigoColegiado) {
        return colegiadoRepository.existsByCuentaConfirmadaFalseAndCodigoColegiado(codigoColegiado);
    }

    @Transactional
    public void confirmarCuenta(String codigoColegiado) {
        colegiadoRepository.confirmarCuenta(codigoColegiado);
    }

}
