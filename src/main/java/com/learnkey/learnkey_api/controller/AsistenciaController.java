package com.learnkey.learnkey_api.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

import com.learnkey.learnkey_api.model.Asistencia;
import com.learnkey.learnkey_api.repository.AsistenciaRepository;

/**
 * Controlador REST para la gestión de asistencias.
 * Proporciona endpoints para CRUD de asistencias, incluyendo registro de
 * entrada y salida.
 */
@RestController
@RequestMapping("/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    /**
     * Obtiene todas las asistencias.
     *
     * @return Lista de asistencias
     */
    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    /**
     * Obtiene una asistencia por su id.
     *
     * @param id_asistencia Identificador de la asistencia
     * @return Asistencia encontrada o 404 si no existe
     */
    @GetMapping("/{id_asistencia}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Integer id_asistencia) {
        Optional<Asistencia> asistencia = asistenciaRepository.findById(id_asistencia);
        return asistencia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Registra la entrada de un alumno en un curso.
     *
     * @param asistencia Datos de la asistencia
     * @return Asistencia registrada
     */
    @PostMapping("/entrada")
    public Asistencia registrarEntrada(@RequestBody Asistencia asistencia) {
        asistencia.setHoraSalida(null); // salida vacía al inicio

        if (asistencia.getToken() == null || asistencia.getToken().isEmpty()) {
            asistencia.setToken(UUID.randomUUID().toString());
        }

        // Aquí podrías agregar validaciones de duplicados si quieres

        return asistenciaRepository.save(asistencia);
    }

    /**
     * Registra la salida de un alumno de un curso.
     *
     * @param id                Identificador de la asistencia
     * @param asistenciaDetails Datos con hora de salida
     * @return Asistencia actualizada o 404 si no existe
     */
    @PutMapping("/salida/{id}")
    public ResponseEntity<Asistencia> registrarSalida(@PathVariable Integer id,
            @RequestBody Asistencia asistenciaDetails) {
        Optional<Asistencia> optionalAsistencia = asistenciaRepository.findById(id);
        if (optionalAsistencia.isPresent()) {
            Asistencia asistencia = optionalAsistencia.get();
            asistencia.setHoraEntrada(asistenciaDetails.getHoraEntrada());
            asistencia.setHoraSalida(asistenciaDetails.getHoraSalida());
            Asistencia updated = asistenciaRepository.save(asistencia);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina una asistencia por su id.
     *
     * @param id Identificador de la asistencia
     * @return 204 si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Integer id) {
        if (asistenciaRepository.existsById(id)) {
            asistenciaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
