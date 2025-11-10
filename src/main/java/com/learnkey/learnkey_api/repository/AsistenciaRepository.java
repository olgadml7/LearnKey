package com.learnkey.learnkey_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnkey.learnkey_api.model.Asistencia;

public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {

    // Buscar asistencias por alumno
    List<Asistencia> findByAlumno_Id_alumno(Long idAlumno);

    // Buscar asistencias por curso
    List<Asistencia> findByCurso_Id_curso(Long idCurso);

    // Buscar asistencias por fecha
    List<Asistencia> findByFecha(String fecha);

    // Buscar asistencia por token
    Asistencia findByToken(String token);

    // Buscar asistencias por rango de fechas
    List<Asistencia> findByFechaBetween(String fechaInicio, String fechaFin);
}
