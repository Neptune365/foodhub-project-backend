package com.project.FoodHub.service;

import com.project.FoodHub.config.Jwt.JwtService;
import com.project.FoodHub.dto.AuthRequest;
import com.project.FoodHub.dto.AuthResponse;
import com.project.FoodHub.dto.ConfirmacionResponse;
import com.project.FoodHub.dto.CreadorDTO;
import com.project.FoodHub.entity.Creador;
import com.project.FoodHub.entity.Rol;
import com.project.FoodHub.exception.*;
import com.project.FoodHub.mapper.CreadorMapper;
import com.project.FoodHub.registration.token.TokenConfirmacion;
import com.project.FoodHub.registration.token.TokenConfirmacionService;
import com.project.FoodHub.repository.CreadorRepository;
import com.project.FoodHub.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreadorService {

    private final CreadorRepository creadorRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenConfirmacionService tokenConfirmacionService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RecetaRepository recetaRepository;
    private final CreadorMapper creadorMapper;


    public List<Creador> mostrarCreadores() {
        return creadorRepository.findAll();
    }

    public Optional<String> crearCuenta(Creador creador) {
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

        return Optional.of(token);
    }
    public AuthResponse iniciarSesion(AuthRequest authRequest) {
        String identificador = authRequest.getIdentificador();
        String contrasenia = authRequest.getContrasenia();

        Creador creador;

        if (identificador.contains("@")) {
            creador = creadorRepository.findCreadorByCorreoElectronico(identificador)
                    .orElseThrow(() -> new UsuarioNoValidoException("Usuario no válido"));
        } else {
            creador = creadorRepository.findByCodigoColegiatura(identificador)
                    .orElseThrow(() -> new UsuarioNoValidoException("Usuario no válido"));
        }

        if (!creador.getEnabled()) {
            throw new CuentaNoConfirmadaException("Cuenta no confirmada");
        }

        if (passwordEncoder.matches(contrasenia, creador.getContrasenia())) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creador, contrasenia)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwtToken = jwtService.generateToken(creador.getIdCreador().toString(), new HashMap<>(), creador);

            return AuthResponse.builder().token(jwtToken).build();
        } else {
            throw new UsuarioNoValidoException("Credenciales inválidas");
        }
    }

    @Transactional
    public ConfirmacionResponse modificarPerfil(String fotoPerfil){
        Long idCreador = obtenerIdCreadorAutenticado();

        Creador creador = creadorRepository.findByIdCreador(idCreador)
                .orElseThrow(() -> new CreadorNoEncontradoException("Creador no encontrado con ID: " + idCreador));

        creador.setFotoPerfil(fotoPerfil);

        return new ConfirmacionResponse("Foto de perfil actualizada", "success");
    }

    public Integer obtenerCantidadDeRecetasCreadas(){
        Long idCreador = obtenerIdCreadorAutenticado();

        Creador creador = creadorRepository.findByIdCreador(idCreador)
                .orElseThrow(() -> new CreadorNoEncontradoException("Creador no encontrado con ID: " + idCreador));

        return recetaRepository.countByCreador(creador);
    }

    public CreadorDTO obtenerDatosDeCreador() {
        Long idCreador = obtenerIdCreadorAutenticado();

        Creador creador = creadorRepository.findByIdCreador(idCreador)
                .orElseThrow(() -> new CreadorNoEncontradoException("Creador no encontrado con ID: " + idCreador));

        return creadorMapper.mapToDTO(creador);
    }

    public int enableUser(String email) {
        return creadorRepository.enableUser(email);
    }

    private Long obtenerIdCreadorAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        validarAutenticacion(authentication);
        return ((Creador) authentication.getPrincipal()).getIdCreador();
    }

    private void validarAutenticacion(Authentication authentication) {
        Optional.ofNullable(authentication)
                .filter(Authentication::isAuthenticated)
                .orElseThrow(() -> new UsuarioNoAutenticadoException("Usuario no autenticado"));
    }
}
