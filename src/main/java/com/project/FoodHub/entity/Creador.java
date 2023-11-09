package com.project.FoodHub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "creador")
@PrimaryKeyJoinColumn(referencedColumnName = "idUsuario")
public class Creador extends Usuario{

    @Column(name = "codigo_colegiatura", nullable = false)
    private String codigoColegiatura;

}
