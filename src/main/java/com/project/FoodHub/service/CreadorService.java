package com.project.FoodHub.service;

import com.project.FoodHub.config.Jwt.JwtService;
import com.project.FoodHub.dto.AuthRequest;
import com.project.FoodHub.dto.AuthResponse;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Receta;
import com.project.FoodHub.entity.Rol;
import com.project.FoodHub.exception.CorreoExistenteException;
import com.project.FoodHub.registration.token.TokenConfirmacion;
import com.project.FoodHub.registration.token.TokenConfirmacionService;
import com.project.FoodHub.repository.CreadorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreadorService {

    private final CreadorRepository creadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfirmacionService tokenConfirmacionService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


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
    public AuthResponse iniciarSesion(AuthRequest authRequest) {
        String identificador = authRequest.getIdentificador();
        String contrasenia = authRequest.getContrasenia();

        Creador creador;

        if (identificador.contains("@")) {
            creador = creadorRepository.findByCorreoElectronico(identificador);
        } else {
            creador = creadorRepository.findByCodigoColegiatura(identificador);
        }

        if (creador != null && passwordEncoder.matches(contrasenia, creador.getContrasenia())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creador, contrasenia)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtService.generateToken(creador.getIdCreador().toString(), new HashMap<>(), creador);

            return AuthResponse.builder().token(jwtToken).build();
        } else {
            if (creador == null) {
                throw new UsernameNotFoundException("Usuario no encontrado");
            } else {
                throw new BadCredentialsException("Credenciales inválidas");
            }
        }
    }

    @Transactional
    public void modificarPerfil(String fotoPerfil){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AuthenticationCredentialsNotFoundException("Usuario no autenticado");
        }

        String username = authentication.getName();

        Creador creador = creadorRepository.findByCorreoElectronico(username);
        if (creador == null) {
            throw new IllegalArgumentException("Creador no encontrado para el usuario: " + username);
        }

        creador.setFotoPerfil(fotoPerfil);
        creadorRepository.save(creador);
    }

    public Integer obtenerCantidadDeRecetasCreadas(Long idCreador){
        Creador creador = creadorRepository.findByIdCreador(idCreador).orElse(null);

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
