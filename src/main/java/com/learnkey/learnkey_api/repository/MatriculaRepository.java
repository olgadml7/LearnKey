// MatriculaRepository.java
package com.learnkey.learnkey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnkey.learnkey_api.model.Matricula;

/**
 * Interfaz que proporciona acceso a la tabla "matriculas" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    // Buscar matrículas por alumno
    // List<Matricula> findById_alumno(Integer idAlumno);

    // Buscar matrículas por curso
    // List<Matricula> findById_curso(Integer idCurso);
}
