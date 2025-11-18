// AlumnoRepository.java
package com.learnkey.learnkey_api.repository; // Paquete donde se guarda el repositorio de la entidad Alumno

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository proporciona todos los métodos CRUD listos para usar
import org.springframework.stereotype.Repository; // Anotación que marca esta interfaz como un componente de acceso a datos

import com.learnkey.learnkey_api.model.Alumno; // Import de la entidad Alumno, que será gestionada por este repositorio

/**
 * Interfaz que proporciona acceso a la tabla "alumnos" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    // Extiende JpaRepository indicando:
    // - La entidad que maneja: Alumno
    // - El tipo de la clave primaria: Integer

    // Puedes agregar métodos personalizados si los necesitas, por ejemplo:
    // Optional<Alumno> findByDni(String dni);
}
