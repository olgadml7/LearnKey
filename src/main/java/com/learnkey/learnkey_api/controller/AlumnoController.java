package com.learnkey.learnkey_api.controller; // Paquete donde se encuentra este controlador

import java.util.HashMap;
import java.util.List; // Importa la clase List para trabajar con listas de alumnos
import java.util.Map;// Importa Optional para manejar valores potencialmente nulos
import java.util.Optional; // Para inyectar dependencias automáticamente

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus; // Para construir respuestas HTTP complejas (200, 404...)
import org.springframework.http.ResponseEntity; // Para permitir CORS
import org.springframework.web.bind.annotation.CrossOrigin; // Para manejar solicitudes DELETE
import org.springframework.web.bind.annotation.DeleteMapping; // Para manejar solicitudes GET
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping; // Para extraer variables de la URL
import org.springframework.web.bind.annotation.PathVariable; // Para manejar solicitudes POST
import org.springframework.web.bind.annotation.PostMapping; // Para manejar solicitudes PUT
import org.springframework.web.bind.annotation.PutMapping; // Para recibir datos del cuerpo de la petición
import org.springframework.web.bind.annotation.RequestBody; // Para definir la ruta base del controlador
import org.springframework.web.bind.annotation.RequestMapping; // Para indicar que es un controlador REST
import org.springframework.web.bind.annotation.RestController;

import com.learnkey.learnkey_api.model.Alumno; // Importa el modelo Alumno
import com.learnkey.learnkey_api.model.AlumnoPatchDTO; // Importa el repositorio de Alumno
import com.learnkey.learnkey_api.repository.AlumnoRepository;

/**
 * Controlador REST para la gestión de alumnos.
 * Proporciona endpoints para CRUD de alumnos.
 */
@RestController // Indica que esta clase sirve endpoints REST
@RequestMapping("/alumnos") // Ruta base para todas las operaciones relacionadas con alumnos
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (CORS)
public class AlumnoController { // Inicio de la clase AlumnoController

    @Autowired // Anotación para inyectar el repositorio sin necesidad de instanciarlo
               // manualmente
    private AlumnoRepository alumnoRepository; // Objeto que permite interactuar con la base de datos

    /**
     * Obtiene la lista de todos los alumnos.
     *
     * @return Lista de todos los alumnos
     */
    @GetMapping // Maneja solicitudes GET en /alumnos
    public List<Alumno> getAllAlumnos() { // Método que devuelve todos los alumnos existentes
        return alumnoRepository.findAll(); // Recupera la lista completa de alumnos desde la BD
    }

    /**
     * Obtiene un alumno por su id.
     *
     * @param id Identificador del alumno
     * @return Alumno encontrado o 404 si no existe
     */
    @GetMapping("/{id}") // Maneja solicitudes GET con un ID en la URL, ej: /alumnos/3
    public ResponseEntity<Alumno> getAlumnoById(@PathVariable Integer id) { // @PathVariable extrae el ID de la URL
        Optional<Alumno> alumno = alumnoRepository.findById(id); // Busca el alumno en la BD usando Optional
        return alumno.map(ResponseEntity::ok) // Si existe, devuelve 200 OK con el alumno
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    /**
     * Crea un nuevo alumno.
     *
     * @param alumno Datos del alumno a crear
     * @return Alumno creado
     */
    @PostMapping // Maneja solicitudes POST a /alumnos
    public Alumno createAlumno(@RequestBody Alumno alumno) { // @RequestBody toma los datos enviados en JSON
        return alumnoRepository.save(alumno); // Guarda el alumno en la BD y devuelve la entidad creada

    }

    /**
     * Actualiza un alumno existente.
     *
     * @param id            Identificador del alumno a actualizar
     * @param alumnoDetails Datos actualizados del alumno
     * @return Alumno actualizado o 404 si no existe
     */
    @PutMapping("/{id}") // Maneja solicitudes PUT a /alumnos/{id}
    public ResponseEntity<Alumno> updateAlumno(@PathVariable Integer id, @RequestBody Alumno alumnoDetails) {
        Optional<Alumno> optionalAlumno = alumnoRepository.findById(id); // Busca el alumno existente por ID
        if (optionalAlumno.isPresent()) { // Si existe el alumno
            Alumno alumno = optionalAlumno.get(); // Recupera el objeto Alumno de Optional
            alumno.setNombre(alumnoDetails.getNombre()); // actualiza nombre
            alumno.setApellidos(alumnoDetails.getApellidos()); // Actualiza los apellidos
            alumno.setDni(alumnoDetails.getDni()); // Actualiza dni
            alumno.setFecha_nacimiento(alumnoDetails.getFecha_nacimiento()); // Actualiza fecha de nacimiento
            alumno.setEmail(alumnoDetails.getEmail()); // Actualiza email
            alumno.setTelefono(alumnoDetails.getTelefono()); // Actualiza telefono
            Alumno updated = alumnoRepository.save(alumno); // Guarda los cambios en la base de datos
            return ResponseEntity.ok(updated); // DEvolvera un 200 si se ha actualiza correctamente
        } else { // si no encuentra al alumno
            return ResponseEntity.notFound().build(); // Devuelve un 404 no encontrado
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Alumno> actualizarAlumnoParcial(
            @PathVariable Integer id,
            @RequestBody AlumnoPatchDTO patchDTO) {

        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Actualizar solo los campos que no sean null
        if (patchDTO.getNombre() != null) {
            alumno.setNombre(patchDTO.getNombre());
        }
        if (patchDTO.getApellidos() != null) {
            alumno.setApellidos(patchDTO.getApellidos());
        }
        if (patchDTO.getEmail() != null) {
            alumno.setEmail(patchDTO.getEmail());
        }

        Alumno actualizado = alumnoRepository.save(alumno);
        return ResponseEntity.ok(actualizado);
    }

    /**
     * Elimina un alumno por su id.
     *
     * @param id Identificador del alumno a eliminar
     * @return 204 si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}") // Maneja solicitudes DELETE a /alumnos/{id}
    public ResponseEntity<Map<String, String>> deleteAlumno(@PathVariable Integer id) {
        Map<String, String> response = new HashMap<>();

        if (!alumnoRepository.existsById(id)) {
            response.put("message", "Alumno no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        try {
            alumnoRepository.deleteById(id);
            response.put("message", "Alumno eliminado correctamente");
            return ResponseEntity.ok(response);
        } catch (DataIntegrityViolationException e) {
            // Esto ocurre si hay asistencias asociadas
            response.put("message", "No se puede eliminar el alumno: tiene registros de asistencia asociados");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

    }
}
