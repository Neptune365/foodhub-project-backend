package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ingrediente")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingrediente_id")
    private Long id;

    @Column(name = "ingrediente", nullable = false)
    private String ingrediente;

//    @ManyToOne
//    @JoinColumn(name = "receta_id")
//    private Receta receta;
}
