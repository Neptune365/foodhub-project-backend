package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creador")
public class Creador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "creador_id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellido_paterno", nullable = false)
    private String apellidoPaterno;

    @Column(name = "apellido_materno", nullable = false)
    private String appellidoMaterno;

    @Column(name = "correo_electronico", nullable = false)
    private String correoElectronico;

    @Column(name = "contraseña", nullable = false)
    private String contraseña;

    @Column(name = "codigo_colegiatura", nullable = false)
    private String codigoColegiatura;

}
