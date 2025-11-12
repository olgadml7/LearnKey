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

import com.learnkey.learnkey_api.model.Alumno;
import com.learnkey.learnkey_api.repository.AlumnoRepository;

/**
 * Controlador REST para la gestión de alumnos.
 * Proporciona endpoints para CRUD de alumnos.
 */
@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    /**
     * Obtiene la lista de todos los alumnos.
     *
     * @return Lista de todos los alumnos
     */
    @GetMapping
    public List<Alumno> getAllAlumnos() {
        return alumnoRepository.findAll();
    }

    /**
     * Obtiene un alumno por su id.
     *
     * @param id Identificador del alumno
     * @return Alumno encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Integer id) {
        Optional<Alumno> alumno = alumnoRepository.findById(id);
        return alumno.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo alumno.
     *
     * @param alumno Datos del alumno a crear
     * @return Alumno creado
     */
    @PostMapping
    public Alumno createAlumno(@RequestBody Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    /**
     * Actualiza un alumno existente.
     *
     * @param id            Identificador del alumno a actualizar
     * @param alumnoDetails Datos actualizados del alumno
     * @return Alumno actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Integer id, @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(id);
        if (optionalAlumno.isPresent()) {
            Alumno alumno = optionalAlumno.get();
            alumno.setNombre(alumnoDetails.getNombre());
            alumno.setApellidos(alumnoDetails.getApellidos());
            alumno.setDni(alumnoDetails.getDni());
            alumno.setFecha_nacimiento(alumnoDetails.getFecha_nacimiento());
            alumno.setEmail(alumnoDetails.getEmail());
            alumno.setTelefono(alumnoDetails.getTelefono());
            Alumno updated = alumnoRepository.save(alumno);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un alumno por su id.
     *
     * @param id Identificador del alumno a eliminar
     * @return 204 si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlumno(@PathVariable Integer id) {
        if (alumnoRepository.existsById(id)) {
            alumnoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
