package com.project.FoodHub.repository;

import com.project.FoodHub.entity.Colegiado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ColegiadoRepository extends JpaRepository<Colegiado, Long> {
    boolean existsByNombreColegiadoAndApellidoPaternoColegiadoAndAppellidoMaternoColegiadoAndCodigoColegiado(
            String nombre, String apellidoPaterno, String apellidoMaterno, String codigoColegiado);

    boolean existsByCuentaConfirmadaFalseAndCodigoColegiado(String codigoColegiado);

    @Modifying
    @Query("UPDATE Colegiado c " +
            "SET c.cuentaConfirmada = true " +
            "WHERE c.codigoColegiado = :codigoColegiado")
    void confirmarCuenta(@Param("codigoColegiado") String codigoColegiado);

    boolean existsByCodigoColegiado(String codigoColegiado);
}
