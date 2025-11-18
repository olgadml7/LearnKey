// AdministradorRepository.java
package com.learnkey.learnkey_api.repository; // Paquete donde se encuentra el repositorio

import org.springframework.data.jpa.repository.JpaRepository; // Import para usar JpaRepository
import org.springframework.stereotype.Repository; // Import para marcar la interfaz como repositorio

import com.learnkey.learnkey_api.model.Administrador; // Import de la entidad Administrador

/**
 * Interfaz que proporciona acceso a la tabla "administradores" en la base de
 * datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository // Indica que esta interfaz es un componente de acceso a datos (DAO/Repositorio)
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    // Extiende JpaRepository indicando:
    // - La entidad que administra: Administrador
    // - El tipo de su clave primaria: Integer

    // Métodos adicionales si los necesitas, por ejemplo:
    // Optional<Administrador> findByEmail(String email); // Buscar por email
}
