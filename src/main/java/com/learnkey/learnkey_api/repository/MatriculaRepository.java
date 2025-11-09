package com.learnkey.learnkey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnkey.learnkey_api.model.Matricula;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
}
