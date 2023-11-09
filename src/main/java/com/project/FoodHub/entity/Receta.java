package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "ingredientes", nullable = false)
    private List ingredientes;

    @Column(name = "instrucciones", nullable = false)
    private List instrucciones;

    @Column(name = "tiempo_coccion", nullable = false)
    private Integer tiempoCoccion;

    @Column(name = "porciones", nullable = false)
    private Integer porciones;

    @Column(name = "calorias", nullable = false)
    private Double calorias;

    @Column(name = "imagen", nullable = false)
    private String imagen;

    @Column(name = "categoria", nullable = false)
    private String categoria;

    public Receta(String titulo, String imagen, String descripcion) {
        this.titulo = titulo;
        this.imagen = imagen;
        this.descripcion = descripcion;
    }

}
