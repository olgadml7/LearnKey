// AlumnoRepository.java
package com.learnkey.learnkey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnkey.learnkey_api.model.Alumno;

/**
 * Interfaz que proporciona acceso a la tabla "alumnos" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    // Optional<Alumno> findByDni(String dni);
}
