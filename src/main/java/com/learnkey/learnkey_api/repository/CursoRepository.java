// CursoRepository.java
package com.learnkey.learnkey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnkey.learnkey_api.model.Curso;

/**
 * Interfaz que proporciona acceso a la tabla "cursos" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
}
