package com.project.FoodHub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "colegiado")
public class Colegiado {

//    PROPIO DE LA ENTIDAD - BASE DE DATOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//
    private String nombreColegiado;
    private String apellidoPaternoColegiado;
    private String appellidoMaternoColegiado;
    private String codigoColegiado;

}
