package com.project.FoodHub.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "instruccion")
public class Instruccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instruccion")
    private Long idInstruccion;

    @Column(name = "instruccion", nullable = false)
    private String instruccion;

    @ManyToOne
    @JoinColumn(name = "id_receta")
    @JsonIgnore
    private Receta receta;
}
