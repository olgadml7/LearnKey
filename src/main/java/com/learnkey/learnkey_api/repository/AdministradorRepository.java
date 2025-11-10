package com.learnkey.learnkey_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learnkey.learnkey_api.model.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    Optional<Administrador> findByEmail(String email);
}
