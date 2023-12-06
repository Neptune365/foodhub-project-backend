package com.project.FoodHub.registration;

import com.project.FoodHub.dto.ConfirmacionResponse;
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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistroService {

    private final ColegiadoService colegiadoService;
    private final CreadorService creadorService;
    private final EmailSender emailSender;
    private final TokenConfirmacionService tokenConfirmacionService;


    public ConfirmacionResponse registrar(CreadorRequest request) {
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

        Optional<String> tokenOptional = creadorService.crearCuenta(creador);
        String token = tokenOptional.orElseThrow(() -> new CuentaNoCreadaException("No se pudo crear la cuenta correctamente."));

        String link = "https://foodhub-backend-4thr.onrender.com/auth/confirm?token=" + token;
        emailSender.enviarConfirmacionCuenta(request.getCorreoElectronico(), request.getNombre(), link);

        return new ConfirmacionResponse("Creación de cuenta exitosa", "success");
    }

    @Transactional
    public ConfirmacionResponse confirmarToken(String token) {
        TokenConfirmacion tokenConfirmacion = tokenConfirmacionService
                .getToken(token)
                .orElseThrow(() ->
                        new TokenNoEncontradoException("Token no encontrado"));

        if (tokenConfirmacion.getConfirmedAt() != null) {
            return new ConfirmacionResponse("Token ya confirmado", "error");
        }

        LocalDateTime expiredAt = tokenConfirmacion.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            return new ConfirmacionResponse("Token expirado", "error");
        }

        tokenConfirmacionService.setConfirmedAt(token);

        if (colegiadoService.isCuentaConfirmada(tokenConfirmacion.getCreador().getCodigoColegiatura())) {
            colegiadoService.confirmarCuenta(tokenConfirmacion.getCreador().getCodigoColegiatura());
            creadorService.enableUser(tokenConfirmacion.getCreador().getCorreoElectronico());
            return new ConfirmacionResponse("Confirmación exitosa", "success");
        } else {
            throw new CorreoConfirmadoException("Código de colegiado ya registrado");
        }

    }

}
