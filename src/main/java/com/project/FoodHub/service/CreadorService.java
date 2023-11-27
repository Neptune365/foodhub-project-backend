package com.project.FoodHub.service;

import com.project.FoodHub.dto.CreadorDTO;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.entity.Rol;
import com.project.FoodHub.exception.CorreoExistenteException;
import com.project.FoodHub.registration.token.TokenConfirmacion;
import com.project.FoodHub.registration.token.TokenConfirmacionService;
import com.project.FoodHub.repository.CreadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreadorService {

    private final CreadorRepository creadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfirmacionService tokenConfirmacionService;

    public List<Creador> mostrarCreadores() {
        return creadorRepository.findAll();
    }

    public String crearCuenta(Creador creador) {
        if (creadorRepository.findCreadorByCorreoElectronico(creador.getCorreoElectronico()).isPresent()) {
            throw new CorreoExistenteException("Correo ingresado ya existe");
        }

        creador.setContrasenia(passwordEncoder.encode(creador.getContrasenia()));
        creador.setRole(Rol.USER);

        creadorRepository.save(creador);

        String token = UUID.randomUUID().toString();
        TokenConfirmacion tokenConfirmacion = new TokenConfirmacion(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                creador
        );

        tokenConfirmacionService.saveConfirmationToken(tokenConfirmacion);

        return token;

    }
    public void iniciarSesion(CreadorDTO creadorDTO) {
        String identificador = creadorDTO.getIdentificador();
        String contrasena = creadorDTO.getContrasena();
        Creador creador = null;

        if (identificador.contains("@")) {
            creador = creadorRepository.findByCorreoElectronico(identificador);
        } else {
            creador = creadorRepository.findByCodigoColegiatura(identificador);
        }

        if (creador != null && creador.getContrasenia().equals(contrasena)) {
            System.out.println("Inicio de sesión exitoso para " + identificador);
        } else {
            System.out.println("Inicio de sesión fallido para " + identificador);
        }
    }

    public Integer obtenerCantidadDeRecetasCreadas(Long creadorId){
        Creador creador = creadorRepository.findById(creadorId).orElse(null);

        if (creador != null) {
            List<Receta> recetasDelCreador = creador.getRecetas();
            if (recetasDelCreador != null) {
                return recetasDelCreador.size();
            }
        }

        return 0;
    }

    public int enableUser(String email) {
        return creadorRepository.enableUser(email);
    }
}
