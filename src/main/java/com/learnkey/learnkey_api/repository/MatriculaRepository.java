// MatriculaRepository.java
package com.learnkey.learnkey_api.repository; // Paquete donde se encuentra el repositorio de la entidad Matricula

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository proporciona todos los métodos CRUD listos para usar
import org.springframework.stereotype.Repository; // Marca esta interfaz como un componente de acceso a datos (repositorio)

import com.learnkey.learnkey_api.model.Matricula; // Importa la entidad Matricula que será gestionada por este repositorio

/**
 * Interfaz que proporciona acceso a la tabla "matriculas" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository // Indica que esta interfaz es un repositorio para que Spring la detecte
            // automáticamente
public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {

    // Extiende JpaRepository indicando:
    // - La entidad gestionada: Matricula
    // - El tipo de clave primaria: Integer

    // Métodos personalizados comentados que podrías usar:
    // Buscar matrículas por alumno
    // List<Matricula> findById_alumno(Integer idAlumno);
    // Buscar matrículas por curso
    // List<Matricula> findById_curso(Integer idCurso);
}
