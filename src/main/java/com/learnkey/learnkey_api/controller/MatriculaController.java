package com.learnkey.learnkey_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnkey.learnkey_api.model.Matricula;
import com.learnkey.learnkey_api.repository.MatriculaRepository;

/**
 * Controlador REST para la gestión de matrículas.
 * Proporciona endpoints para CRUD de matrículas.
 */
@RestController
@RequestMapping("/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    /**
     * Obtiene todas las matrículas.
     *
     * @return Lista de matrículas
     */
    @GetMapping
    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    /**
     * Obtiene una matrícula por su id.
     *
     * @param id Identificador de la matrícula
     * @return Matrícula encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable Integer id) {
        Optional<Matricula> matricula = matriculaRepository.findById(id);
        return matricula.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea una nueva matrícula.
     *
     * @param matricula Datos de la matrícula a crear
     * @return Matrícula creada
     */
    @PostMapping
    public Matricula createMatricula(@RequestBody Matricula matricula) {
        return matriculaRepository.save(matricula);
    }

    /**
     * Actualiza una matrícula existente.
     *
     * @param id               Identificador de la matrícula a actualizar
     * @param matriculaDetails Datos actualizados de la matrícula
     * @return Matrícula actualizada o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Matricula> updateMatricula(@PathVariable Integer id,
            @RequestBody Matricula matriculaDetails) {
        Optional<Matricula> optionalMatricula = matriculaRepository.findById(id);
        if (optionalMatricula.isPresent()) {
            Matricula matricula = optionalMatricula.get();
            matricula.setAlumnoId(matriculaDetails.getAlumnoId());
            matricula.setCursoId(matriculaDetails.getCursoId());
            matricula.setFecha_alta(matriculaDetails.getFecha_alta());
            Matricula updated = matriculaRepository.save(matricula);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una matrícula por su id.
     *
     * @param id Identificador de la matrícula a eliminar
     * @return 204 si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatricula(@PathVariable Integer id) {
        if (matriculaRepository.existsById(id)) {
            matriculaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
