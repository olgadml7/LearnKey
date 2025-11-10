package com.learnkey.learnkey_api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learnkey.learnkey_api.model.Asistencia;
import com.learnkey.learnkey_api.repository.AsistenciaRepository;

@RestController
@RequestMapping("/asistencias")
@CrossOrigin(origins = "*")
public class AsistenciaController {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    // Obtener todas las asistencias
    @GetMapping
    public List<Asistencia> getAllAsistencias() {
        return asistenciaRepository.findAll();
    }

    // Obtener asistencia por id
    @GetMapping("/{id}")
    public Asistencia getAsistenciaById(@PathVariable Long id) {
        return asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + id));
    }

    // Crear nueva asistencia (registrar entrada)
    @PostMapping("/entrada")
    public Asistencia registrarEntrada(@RequestBody Asistencia asistencia) {
        asistencia.setHoraSalida(null); // salida vacía al inicio

        // Generar token si no se envía
        if (asistencia.getToken() == null || asistencia.getToken().isEmpty()) {
            asistencia.setToken(UUID.randomUUID().toString());
        }

        // Verificar duplicado: mismo alumno, curso y fecha
        boolean existe = asistenciaRepository.findByAlumno_Id_alumno(asistencia.getAlumno().getId_alumno())
                .stream()
                .anyMatch(a -> a.getCurso().getId_curso().equals(asistencia.getCurso().getId_curso())
                        && a.getFecha().equals(asistencia.getFecha()));

        if (existe) {
            throw new RuntimeException(
                    "Ya existe una entrada para este alumno en este curso y fecha.");
        }

        return asistenciaRepository.save(asistencia);
    }

    // Actualizar asistencia (registrar salida)
    @PutMapping("/salida/{id}")
    public Asistencia registrarSalida(@PathVariable Long id, @RequestBody Asistencia asistenciaDetalles) {
        Asistencia asistencia = asistenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistencia no encontrada con id: " + id));

        asistencia.setHoraSalida(asistenciaDetalles.getHoraSalida());
        return asistenciaRepository.save(asistencia);
    }

    // Consultar asistencias por alumno
    @GetMapping("/alumno/{idAlumno}")
    public List<Asistencia> getAsistenciasPorAlumno(@PathVariable Long idAlumno) {
        return asistenciaRepository.findByAlumno_Id_alumno(idAlumno);
    }

    // Consultar asistencias por curso
    @GetMapping("/curso/{idCurso}")
    public List<Asistencia> getAsistenciasPorCurso(@PathVariable Long idCurso) {
        return asistenciaRepository.findByCurso_Id_curso(idCurso);
    }

    // Consultar asistencias por fecha
    @GetMapping("/fecha/{fecha}")
    public List<Asistencia> getAsistenciasPorFecha(@PathVariable String fecha) {
        return asistenciaRepository.findByFecha(fecha);
    }

    // Consultar asistencia por token
    @GetMapping("/token/{token}")
    public Asistencia getAsistenciaPorToken(@PathVariable String token) {
        return asistenciaRepository.findByToken(token);
    }

    // Consultar asistencias por rango de fechas
    @GetMapping("/rango")
    public List<Asistencia> getAsistenciasPorRango(
            @RequestParam String fechaInicio,
            @RequestParam String fechaFin) {
        return asistenciaRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    // Eliminar asistencia
    @DeleteMapping("/{id}")
    public String deleteAsistencia(@PathVariable Long id) {
        asistenciaRepository.deleteById(id);
        return "Asistencia eliminada con id: " + id;
    }
}
