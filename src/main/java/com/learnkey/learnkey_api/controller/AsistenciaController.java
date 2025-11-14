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

@RestController
@RequestMapping("/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    // ==============================================
    // GET - Lista de todas las asistencias
    // ==============================================
    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    // ==============================================
    // GET - Obtener asistencia por ID
    // ==============================================
    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Integer id) {
        return asistenciaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ==============================================
    // POST - Registrar entrada de alumno
    // ==============================================
    @PostMapping("/entrada")
    public Asistencia registrarEntrada(@RequestBody Asistencia asistencia) {

        asistencia.setHoraSalida(null); // salida vacía al registrar entrada

        if (asistencia.getToken() == null || asistencia.getToken().isEmpty()) {
            asistencia.setToken(UUID.randomUUID().toString());
        }

        return asistenciaRepository.save(asistencia);
    }

    // ==============================================
    // PUT - Registrar salida de alumno
    // ==============================================
    @PutMapping("/salida/{id}")
    public ResponseEntity<Asistencia> registrarSalida(
            @PathVariable Integer id,
            @RequestBody Asistencia asistenciaDetails) {

        Optional<Asistencia> optionalAsistencia = asistenciaRepository.findById(id);

        if (optionalAsistencia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Asistencia asistencia = optionalAsistencia.get();
        asistencia.setHoraSalida(asistenciaDetails.getHoraSalida());

        Asistencia updated = asistenciaRepository.save(asistencia);
        return ResponseEntity.ok(updated);
    }

    // ==============================================
    // PUT - Corrección manual (profesor)
    // ==============================================
    @PutMapping("/{id}")
    public ResponseEntity<Asistencia> actualizarAsistencia(
            @PathVariable Integer id,
            @RequestBody Asistencia asistenciaDetails) {

        Optional<Asistencia> optionalAsistencia = asistenciaRepository.findById(id);

        if (optionalAsistencia.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Asistencia asistencia = optionalAsistencia.get();

        // Actualización parcial de campos
        if (asistenciaDetails.getHoraEntrada() != null) {
            asistencia.setHoraEntrada(asistenciaDetails.getHoraEntrada());
        }
        if (asistenciaDetails.getHoraSalida() != null) {
            asistencia.setHoraSalida(asistenciaDetails.getHoraSalida());
        }
        if (asistenciaDetails.getFecha() != null) {
            asistencia.setFecha(asistenciaDetails.getFecha());
        }
        if (asistenciaDetails.getId_alumno() != null) {
            asistencia.setId_alumno(asistenciaDetails.getId_alumno());
        }
        if (asistenciaDetails.getId_curso() != null) {
            asistencia.setId_curso(asistenciaDetails.getId_curso());
        }

        Asistencia updated = asistenciaRepository.save(asistencia);
        return ResponseEntity.ok(updated);
    }

    // ==============================================
    // DELETE - Eliminar asistencia por ID
    // ==============================================
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Integer id) {
        if (asistenciaRepository.existsById(id)) {
            asistenciaRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
