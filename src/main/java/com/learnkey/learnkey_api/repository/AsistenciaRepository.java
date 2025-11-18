// AsistenciaRepository.java
package com.learnkey.learnkey_api.repository; // Paquete donde se almacena el repositorio de la entidad Asistencia

import java.util.List; // Importa la clase List, usada para devolver listas de asistencias

import org.springframework.data.jpa.repository.JpaRepository; // JpaRepository proporciona todos los métodos CRUD sin necesidad de implementarlos
import org.springframework.stereotype.Repository; // Anotación que indica que esta interfaz es un componente de acceso a datos

import com.learnkey.learnkey_api.model.Asistencia; // Import de la entidad Asistencia que será gestionada por este repositorio

/**
 * Interfaz que proporciona acceso a la tabla "asistencia" en la base de datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository
// Marca esta interfaz como repositorio para que Spring la detecte
// automáticamente
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {

    // Extiende JpaRepository indicando:
    // - La entidad manejada: Asistencia
    // - El tipo de clave primaria: Integer

    // Buscar asistencias por fecha
    List<Asistencia> findByFecha(String fecha);
    // Método automático generado por Spring Data JPA según el nombre
    // SELECT * FROM asistencia WHERE fecha = ?

    // Buscar asistencia por token
    Asistencia findByToken(String token);
    // Devuelve una única asistencia que coincida con el token
    // SELECT * FROM asistencia WHERE token = ?

    // Buscar asistencias por rango de fechas
    List<Asistencia> findByFechaBetween(String fechaInicio, String fechaFin);
    // Busca todas las asistencias cuya fecha esté entre dos valores
    // SELECT * FROM asistencia WHERE fecha BETWEEN ? AND ?
}
