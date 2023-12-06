package com.project.FoodHub.service;

import com.project.FoodHub.exception.ColegiadoNoEncontradoException;
import com.project.FoodHub.repository.ColegiadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColegiadoService {

    private final ColegiadoRepository colegiadoRepository;

    public boolean validarColegiado(String nombre, String apellidoPaterno, String apellidoMaterno, String codigoColegiado) {
        return colegiadoRepository.existsByNombreColegiadoAndApellidoPaternoColegiadoAndAppellidoMaternoColegiadoAndCodigoColegiado(
                nombre, apellidoPaterno, apellidoMaterno, codigoColegiado);
    }

    @Transactional
    public boolean isCuentaConfirmada(String codigoColegiado) {
        return colegiadoRepository.existsByCuentaConfirmadaFalseAndCodigoColegiado(codigoColegiado);
    }

    @Transactional
    public void confirmarCuenta(String codigoColegiado) {
        if (colegiadoRepository.existsByCodigoColegiado(codigoColegiado)) {
            colegiadoRepository.confirmarCuenta(codigoColegiado);
        } else {
            throw new ColegiadoNoEncontradoException("Colegiado no encontrado con código: " + codigoColegiado);
        }
    }

}
