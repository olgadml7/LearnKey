package com.learnkey.learnkey_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learnkey.learnkey_api.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
