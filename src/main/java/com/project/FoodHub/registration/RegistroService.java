package com.project.FoodHub.registration;

import com.project.FoodHub.dto.CreadorRequest;
import com.project.FoodHub.email.EmailSender;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.exception.ColegiadoNoValidoException;
import com.project.FoodHub.exception.CuentaNoCreadaException;
import com.project.FoodHub.service.ColegiadoService;
import com.project.FoodHub.service.CreadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final ColegiadoService colegiadoService;
    private final CreadorService creadorService;
    private final EmailSender emailSender;


    public String registrar(CreadorRequest request) {
        if (!colegiadoService.validarColegiado(request.getNombre(), request.getApellidoPaterno(), request.getApellidoMaterno(), request.getCodigoColegiatura())) {
            throw new ColegiadoNoValidoException("No se pudo validar el colegiado. Verifica los datos proporcionados.");
        }

        Creador creador = new Creador(
                request.getNombre(),
                request.getApellidoPaterno(),
                request.getApellidoMaterno(),
                request.getEmail(),
                request.getContrasenia(),
                request.getCodigoColegiatura()
        );

        String token = creadorService.crearCuenta(creador);

        if (token == null || token.isEmpty()) {
            throw new CuentaNoCreadaException("No se pudo crear la cuenta correctamente.");
        }

        String link = "http://localhost:8081/api/auth/confirm?token=" + token;
        emailSender.enviarConfirmacionCuenta(request.getEmail(), request.getNombre(), link);

        return "created";
    }

}
