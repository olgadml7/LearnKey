// AsistenciaRepository.java
package com.learnkey.learnkey_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnkey.learnkey_api.model.Asistencia;

/**
 * Interfaz que proporciona acceso a la tabla "asistencia" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {

    // Buscar asistencias por fecha
    List<Asistencia> findByFecha(String fecha);

    // Buscar asistencia por token
    Asistencia findByToken(String token);

    // Buscar asistencias por rango de fechas
    List<Asistencia> findByFechaBetween(String fechaInicio, String fechaFin);
}
