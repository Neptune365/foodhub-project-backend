package com.project.FoodHub.repository;

import com.project.FoodHub.entity.Colegiado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColegiadoRepository extends JpaRepository<Colegiado, Long> {


}
