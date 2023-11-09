package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "receta")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descripcion;
    private List ingredientes;
    private List instrucciones;
    private Integer tiempoCoccion;
    private Integer porciones;
    private Double calorias;
    private String imagen;

}
