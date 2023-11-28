package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "colegiado")
public class Colegiado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colegiado")
    private Long id;

    @Column(name = "nombre_colegiado", nullable = false)
    private String nombreColegiado;

    @Column(name = "apellido_paterno_colegiado", nullable = false)
    private String apellidoPaternoColegiado;

    @Column(name = "apellido_materno_colegiado", nullable = false)
    private String appellidoMaternoColegiado;

    @Column(name = "codigo_colegiado", nullable = false, unique = true)
    private String codigoColegiado;

    @Column(name = "cuenta_confirmada")
    private boolean cuentaConfirmada;


}
