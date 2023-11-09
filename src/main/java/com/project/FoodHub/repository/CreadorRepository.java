package com.project.FoodHub.repository;

import com.project.FoodHub.entity.Creador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreadorRepository extends JpaRepository<Creador, Long> {
}
