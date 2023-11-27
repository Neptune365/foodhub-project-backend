package com.project.FoodHub.registration;

import com.project.FoodHub.dto.CreadorRequest;
import com.project.FoodHub.email.EmailSender;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.exception.ColegiadoNoValidoException;
import com.project.FoodHub.exception.CorreoConfirmadoException;
import com.project.FoodHub.exception.CuentaNoCreadaException;
import com.project.FoodHub.exception.TokenNoEncontradoException;
import com.project.FoodHub.exception.TokenExpiradoException;
import com.project.FoodHub.registration.token.TokenConfirmacion;
import com.project.FoodHub.registration.token.TokenConfirmacionService;
import com.project.FoodHub.service.ColegiadoService;
import com.project.FoodHub.service.CreadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final ColegiadoService colegiadoService;
    private final CreadorService creadorService;
    private final EmailSender emailSender;
    private final TokenConfirmacionService tokenConfirmacionService;


    public String registrar(CreadorRequest request) {
        if (!colegiadoService.validarColegiado(request.getNombre(), request.getApellidoPaterno(), request.getApellidoMaterno(), request.getCodigoColegiatura())) {
            throw new ColegiadoNoValidoException("No se pudo validar el colegiado. Verifica los datos proporcionados.");
        }

        if (!colegiadoService.isCuentaConfirmada(request.getCodigoColegiatura())) {
            throw new CorreoConfirmadoException("Código de colegiado ya registrado");
        }

        Creador creador = new Creador(
                request.getNombre(),
                request.getApellidoPaterno(),
                request.getApellidoMaterno(),
                request.getCorreoElectronico(),
                request.getContrasenia(),
                request.getCodigoColegiatura()
        );

        String token = creadorService.crearCuenta(creador);

        if (token == null || token.isEmpty()) {
            throw new CuentaNoCreadaException("No se pudo crear la cuenta correctamente.");
        }

        String link = "http://localhost:8083/auth/confirm?token=" + token;
        emailSender.enviarConfirmacionCuenta(request.getCorreoElectronico(), request.getNombre(), link);

        return "created";
    }

    @Transactional
    public String confirmarToken(String token) {
        TokenConfirmacion tokenConfirmacion = tokenConfirmacionService
                .getToken(token)
                .orElseThrow(() ->
                        new TokenNoEncontradoException("Token no encontrado"));

        if (tokenConfirmacion.getConfirmedAt() != null) {
            throw new CorreoConfirmadoException("Correo electrónico ya confirmado");
        }

        LocalDateTime expiredAt = tokenConfirmacion.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new TokenExpiradoException("Token expirado");
        }

        tokenConfirmacionService.setConfirmedAt(token);

        if (colegiadoService.isCuentaConfirmada(tokenConfirmacion.getCreador().getCodigoColegiatura())) {
            colegiadoService.confirmarCuenta(tokenConfirmacion.getCreador().getCodigoColegiatura());
            creadorService.enableUser(tokenConfirmacion.getCreador().getCorreoElectronico());
            return "confirmed";
        } else {
            // La cuenta ya ha sido confirmada previamente
            throw new CorreoConfirmadoException("Código de colegiado ya registrado");
        }

    }

}
