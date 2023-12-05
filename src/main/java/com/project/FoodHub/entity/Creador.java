package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creador")
public class Creador implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_creador")
    private Long idCreador;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String apellidoMaterno;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "contraseña", nullable = false)
    private String contrasenia;

    @Column(name = "codigo_colegiatura", nullable = false)
    private String codigoColegiatura;

    @OneToMany(mappedBy = "creador")
    private List<Receta> recetas;

    @Enumerated(EnumType.STRING)
    private Rol role;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @Column(name = "foto_perfil", columnDefinition = "LONGTEXT")
    private String fotoPerfil;

    public Creador(String nombre,
                   String apellidoPaterno,
                   String apellidoMaterno,
                   String email,
                   String contrasenia,
                   String codigoColegiatura) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.correoElectronico = email;
        this.contrasenia = contrasenia;
        this.codigoColegiatura = codigoColegiatura;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return correoElectronico;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
