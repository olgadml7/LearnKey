package com.learnkey.learnkey_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnkey.learnkey_api.model.Matricula;
import com.learnkey.learnkey_api.repository.MatriculaRepository;

@RestController
@RequestMapping("/matriculas")
@CrossOrigin(origins = "*")
public class MatriculaController {

    @Autowired
    private MatriculaRepository matriculaRepository;

    // Obtener todas las matrículas
    @GetMapping
    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.findAll();
    }

    // Obtener matrícula por id
    @GetMapping("/{id}")
    public Matricula getMatriculaById(@PathVariable Long id) {
        return matriculaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Matrícula no encontrada con id: " + id));
    }

    // // Registrar matrícula (alumno en curso) con validaciones
    // @PostMapping
    // public Matricula createMatricula(@RequestBody Matricula matricula) {
    // // Verificar que el alumno existe
    // Alumno alumno = alumnoRepository.findById(matricula.getAlumnoId())
    // .orElseThrow(() -> new RuntimeException(
    // "Alumno no encontrado con id: " + matricula.getAlumno().getId_alumno()));

    // // Verificar que el curso existe
    // Curso curso = cursoRepository.findById(matricula.getCurso().getId_curso())
    // .orElseThrow(() -> new RuntimeException(
    // "Curso no encontrado con id: " + matricula.getCurso().getId_curso()));

    // // Evitar duplicados: mismo alumno y mismo curso
    // boolean existe =
    // matriculaRepository.findByAlumno_Id_alumno(alumno.getId_alumno())
    // .stream()
    // .anyMatch(m -> m.getCurso().getId_curso().equals(curso.getId_curso()));

    // if (existe) {
    // throw new RuntimeException("El alumno ya está matriculado en este curso.");
    // }

    // matricula.setAlumno(alumno);
    // matricula.setCurso(curso);

    // return matriculaRepository.save(matricula);
    // }

    // // Consultar matrículas por alumno
    // @GetMapping("/alumno/{idAlumno}")
    // public List<Matricula> getMatriculasPorAlumno(@PathVariable Long idAlumno) {
    // return matriculaRepository.findByAlumno_Id_alumno(idAlumno);
    // }

    // // Consultar matrículas por curso
    // @GetMapping("/curso/{idCurso}")
    // public List<Matricula> getMatriculasPorCurso(@PathVariable Long idCurso) {
    // return matriculaRepository.findByCurso_Id_curso(idCurso);
    // }

    // // Eliminar matrícula
    // @DeleteMapping("/{id}")
    // public String deleteMatricula(@PathVariable Long id) {
    // matriculaRepository.deleteById(id);
    // return "Matrícula eliminada con id: " + id;
    // }
}
