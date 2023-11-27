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
    private final TokenConfirmacionService confirmationTokenService;


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

    @Transactional
    public String confirmToken(String token) {
        TokenConfirmacion confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() ->
                        new TokenNoEncontradoException("Token no encontrado"));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new CorreoConfirmadoException("Correo electrónico ya confirmado");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new TokenExpiradoException("Token expirado");
        }

        confirmationTokenService.setConfirmedAt(token);
        creadorService.enableUser(
                confirmationToken.getCreador().getCorreoElectronico());

        return "confirmed";
    }

}
