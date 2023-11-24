package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "instruccion")
public class Instruccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instruccion_id")
    private Long id;

    @Column(name = "instruccion", nullable = false)
    private String instruccion;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;
}
