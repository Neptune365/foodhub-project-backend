package com.project.FoodHub.repository;

import com.project.FoodHub.entity.Instruccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstruccionRepository extends JpaRepository<Instruccion, Long> {
}
