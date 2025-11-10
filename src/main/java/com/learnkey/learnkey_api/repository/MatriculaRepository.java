package com.learnkey.learnkey_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnkey.learnkey_api.model.Matricula;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Buscar matrículas por alumno
    List<Matricula> findByAlumno_Id_alumno(Long idAlumno);

    // Buscar matrículas por curso
    List<Matricula> findByCurso_Id_curso(Long idCurso);
}
