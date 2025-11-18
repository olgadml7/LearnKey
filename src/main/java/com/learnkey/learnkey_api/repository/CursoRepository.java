// CursoRepository.java
package com.learnkey.learnkey_api.repository; // El repositorio pertenece al paquete repository de tu proyecto

import org.springframework.data.jpa.repository.JpaRepository; // Importa JpaRepository, que ya incluye los métodos CRUD completos
import org.springframework.stereotype.Repository; // Marca esta interfaz como un repositorio administrado por Spring

import com.learnkey.learnkey_api.model.Curso; // Importa la entidad Curso que este repositorio manejará

/**
 * Interfaz que proporciona acceso a la tabla "cursos" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository // Permite que Spring detecte y registre este repositorio automáticamente
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    // Extiende JpaRepository indicando:
    // - La entidad gestionada: Curso
    // - El tipo de clave primaria: Integer
    //
    // Al extender JpaRepository obtienes automáticamente métodos como:
    // - findAll()
    // - findById(id)
    // - save(entity)
    // - deleteById(id)
    //
    // Puedes añadir métodos personalizados si los necesitas, por ejemplo:
    // List<Curso> findByNombre(String nombre);

}
