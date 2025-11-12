// AdministradorRepository.java
package com.learnkey.learnkey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnkey.learnkey_api.model.Administrador;

/**
 * Interfaz que proporciona acceso a la tabla "administradores" en la base de
 * datos.
 * JpaRepository ya proporciona métodos CRUD básicos: findAll, findById, save,
 * deleteById.
 */
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Integer> {

    // Métodos adicionales si los necesitas, por ejemplo:
    // Optional<Administrador> findByEmail(String email);
}
