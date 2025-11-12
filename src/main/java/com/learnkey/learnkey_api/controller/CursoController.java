package com.learnkey.learnkey_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnkey.learnkey_api.model.Curso;
import com.learnkey.learnkey_api.repository.CursoRepository;

@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    // Obtener todos los cursos
    @GetMapping
    public List<Curso> getAllCursos() {
        List<Curso> cursos = cursoRepository.findAll();
        cursos.forEach(c -> {
            if (c.getHorasTotales() == null) {
                c.calcularHorasTotales();
            }
        });
        return cursos;
    }

    // Obtener un curso por id
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        Optional<Curso> curso = cursoRepository.findById(id);
        return curso.map(c -> {
            if (c.getHorasTotales() == null) {
                c.calcularHorasTotales();
            }
            return ResponseEntity.ok(c);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo curso
    @PostMapping
    public Curso createCurso(@RequestBody Curso curso) {
        if (curso.getHorasTotales() == null) {
            curso.calcularHorasTotales();
        }
        return cursoRepository.save(curso);
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Integer id, @RequestBody Curso cursoDetails) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            curso.setNombre(cursoDetails.getNombre());
            curso.setDescripcion(cursoDetails.getDescripcion());
            curso.setIdAdministrador(cursoDetails.getIdAdministrador());
            curso.setHoraInicio(cursoDetails.getHoraInicio());
            curso.setHoraFin(cursoDetails.getHoraFin());
            curso.setFechaInicio(cursoDetails.getFechaInicio());
            curso.setFechaFin(cursoDetails.getFechaFin());
            curso.setDiasDeClase(cursoDetails.getDiasDeClase());
            // recalcular horas totales
            curso.calcularHorasTotales();
            Curso updated = cursoRepository.save(curso);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
