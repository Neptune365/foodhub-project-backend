package com.project.FoodHub.repository;

import com.project.FoodHub.entity.Categoria;
import com.project.FoodHub.entity.Receta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {
    List<Receta> findByCategoria(Categoria categoria);
    Optional<Receta> findById(Long idReceta);
}
