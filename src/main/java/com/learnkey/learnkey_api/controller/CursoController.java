package com.learnkey.learnkey_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        cursos.forEach(Curso::calcularHorasTotales); // recalcular para los existentes
        return cursos;
    }

    // // Obtener curso por id
    // @GetMapping("/{id}")
    // public Curso getCursoById(@PathVariable Long id) {
    // Curso curso = cursoRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " +
    // id));
    // curso.calcularHorasTotales();
    // return curso;
    // }

    // // Crear nuevo curso
    // @PostMapping
    // public Curso createCurso(@RequestBody Curso curso) {
    // curso.calcularHorasTotales();
    // return cursoRepository.save(curso);
    // }

    // // Actualizar curso
    // @PutMapping("/{id}")
    // public Curso updateCurso(@PathVariable Long id, @RequestBody Curso
    // cursoDetails) {
    // Curso curso = cursoRepository.findById(id)
    // .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " +
    // id));

    // curso.setNombre(cursoDetails.getNombre());
    // curso.setDescripcion(cursoDetails.getDescripcion());
    // curso.setIdAdministrador(cursoDetails.getIdAdministrador());
    // curso.setHoraInicio(cursoDetails.getHoraInicio());
    // curso.setHoraFin(cursoDetails.getHoraFin());
    // curso.setFechaInicio(cursoDetails.getFechaInicio());
    // curso.setFechaFin(cursoDetails.getFechaFin());
    // curso.setDiasDeClase(cursoDetails.getDiasDeClase());

    // curso.calcularHorasTotales(); // recalculamos horas totales al actualizar

    // return cursoRepository.save(curso);
    // }

    // // Eliminar curso
    // @DeleteMapping("/{id}")
    // public String deleteCurso(@PathVariable Long id) {
    // cursoRepository.deleteById(id);
    // return "Curso eliminado con id: " + id;
    // }
}
