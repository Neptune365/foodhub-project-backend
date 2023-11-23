package com.project.FoodHub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receta_id")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "tiempo_coccion", nullable = false)
    private Integer tiempoCoccion;

    @Column(name = "porciones", nullable = false)
    private Integer porciones;

    @Column(name = "calorias", nullable = false)
    private Double calorias;

    @Column(name = "imagen_receta", nullable = false)
    private String imagenReceta;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    @JsonIgnore
    private Creador creador;

//    @OneToMany(mappedBy = "receta")
//    private List<Ingrediente> ingredientes;
//
//    @OneToMany(mappedBy = "receta")
//    private List<Instruccion> instrucciones;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria", nullable = false)
    private Categoria categoria;

}
