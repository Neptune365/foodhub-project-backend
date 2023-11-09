package com.project.FoodHub.repository;

import com.project.FoodHub.entity.Creador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreadorRepository extends JpaRepository<Creador, Long> {
}
