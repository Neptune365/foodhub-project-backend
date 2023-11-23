package com.project.FoodHub.repository;

import com.project.FoodHub.entity.Categoria;
import com.project.FoodHub.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    List<Receta> findByCategoria(Categoria categoria);
}
