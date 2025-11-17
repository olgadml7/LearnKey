package com.learnkey.learnkey_api.controller; // Paquete donde se encuentra este controlador

import java.util.List; //Importa list para manejar colecciones de asistencias
import java.util.Optional; //Importa Optional para manejar valores potencialmente nulos
import java.util.UUID; //Importa UUID para generar tokens únicos

import org.springframework.beans.factory.annotation.Autowired; // Para inyección automática de dependencias
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP con estados personalizados
import org.springframework.web.bind.annotation.CrossOrigin; // Para permitir solicitudes entre dominios (CORS)
import org.springframework.web.bind.annotation.DeleteMapping; // Para manejar solicitudes DELETE
import org.springframework.web.bind.annotation.GetMapping; // Para manejar solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // Para obtener valores de la URL
import org.springframework.web.bind.annotation.PostMapping; // Para manejar solicitudes POST
import org.springframework.web.bind.annotation.PutMapping; // Para manejar solicitudes PUT
import org.springframework.web.bind.annotation.RequestBody; // Para obtener datos enviados en JSON
import org.springframework.web.bind.annotation.RequestMapping; // Para definir la ruta base del controlador
import org.springframework.web.bind.annotation.RestController; // Para definir un controlador REST

import com.learnkey.learnkey_api.model.Asistencia; // Importa el modelo Asistencia
import com.learnkey.learnkey_api.repository.AsistenciaRepository; // Importa el repositorio de Asistencia

@RestController // Indica que esta clase expone endpoints REST
@RequestMapping("/asistencias") // Ruta base para todos los endpoints de Asistencia
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen (CORS abierto)
public class AsistenciaController { // Inicio de la clase controlador

    @Autowired // Inyección del repositorio sin necesidad de instanciarlo manualmente
    private AsistenciaRepository asistenciaRepository; // Repositorio para interactuar con la BD

    // ==============================================
    // GET - Lista de todas las asistencias
    // ==============================================
    @GetMapping // Maneja solicitudes GET a /asistencias
    public List<Asistencia> getAllAsistencias() { // Método que devuelve todas las asistencias
        return asistenciaRepository.findAll(); // Recupera toda la lista desde BD
    }

    // ==============================================
    // GET - Obtener asistencia por ID
    // ==============================================
    @GetMapping("/{id}") // Maneja solicitudes GET a /asistencias/{id}
    public ResponseEntity<Asistencia> getAsistenciaById(@PathVariable Integer id) { // @PathVariable extrae el ID de la
                                                                                    // URL
        return asistenciaRepository.findById(id) // Busca asistencia en BD
                .map(ResponseEntity::ok) // Si existe, devuelve 200 OK
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    // ==============================================
    // POST - Registrar entrada de alumno
    // ==============================================
    @PostMapping("/entrada") // Maneja solicitudes POST a /asistencias/entrada
    public Asistencia registrarEntrada(@RequestBody Asistencia asistencia) { // Recibe datos de asistencia en JSON

        asistencia.setHoraSalida(null); // salida vacía al registrar entrada

        if (asistencia.getToken() == null || asistencia.getToken().isEmpty()) { // Si no tiene token aún
            asistencia.setToken(UUID.randomUUID().toString()); // Genera token único para validar salida
        }

        return asistenciaRepository.save(asistencia); // Guarda y devuelve la asistencia creada
    }

    // ==============================================
    // PUT - Registrar salida de alumno
    // ==============================================
    @PutMapping("/salida/{id}") // Maneja solicitudes PUT a /asistencias/salida/{id}
    public ResponseEntity<Asistencia> registrarSalida(
            @PathVariable Integer id,
            @RequestBody Asistencia asistenciaDetails) { // Solo contiene la hora de salida

        Optional<Asistencia> optionalAsistencia = asistenciaRepository.findById(id); // Busca asistencia por ID

        if (optionalAsistencia.isEmpty()) { // si no existe la asistencia
            return ResponseEntity.notFound().build(); // Devuelve un 404
        }

        Asistencia asistencia = optionalAsistencia.get(); // Obtiene asistencia existente
        asistencia.setHoraSalida(asistenciaDetails.getHoraSalida()); // establece hora de salida desde json

        Asistencia updated = asistenciaRepository.save(asistencia); // Guarda cambios en bd
        return ResponseEntity.ok(updated); // devuelve 200 ok con asistencia actualizada
    }

    // ==============================================
    // PUT - Corrección manual (profesor)
    // ==============================================
    @PutMapping("/{id}") // Maneja solicitudes PUT a /asistencias/{id}
    public ResponseEntity<Asistencia> actualizarAsistencia(
            @PathVariable Integer id,
            @RequestBody Asistencia asistenciaDetails) { // Contiene campos a modificar manualmente

        Optional<Asistencia> optionalAsistencia = asistenciaRepository.findById(id); // Busca asistencia por ID

        if (optionalAsistencia.isEmpty()) { // si no existe
            return ResponseEntity.notFound().build(); // devuleve 404
        }

        Asistencia asistencia = optionalAsistencia.get(); // Obtiene asistencia existente

        // Actualización parcial de campos : solo modifica lo que viene en JSON (PATH
        // manual)
        if (asistenciaDetails.getHoraEntrada() != null) { // si llega hora de entrada
            asistencia.setHoraEntrada(asistenciaDetails.getHoraEntrada()); // la actualiza
        }
        if (asistenciaDetails.getHoraSalida() != null) { // Si llega hora de salida
            asistencia.setHoraSalida(asistenciaDetails.getHoraSalida()); // La actualiza
        }
        if (asistenciaDetails.getFecha() != null) { // Si llega fecha
            asistencia.setFecha(asistenciaDetails.getFecha()); // La actualiza
        }
        if (asistenciaDetails.getId_alumno() != null) { // Si llega ID de alumno
            asistencia.setId_alumno(asistenciaDetails.getId_alumno()); // Lo actualiza
        }
        if (asistenciaDetails.getId_curso() != null) { // Si llega ID de curso
            asistencia.setId_curso(asistenciaDetails.getId_curso()); // Lo actualiza
        }

        Asistencia updated = asistenciaRepository.save(asistencia); // Guarda asistencia corregida
        return ResponseEntity.ok(updated); // Devuelve 200 OK con los cambios
    }

    // ==============================================
    // DELETE - Eliminar asistencia por ID
    // ==============================================
    @DeleteMapping("/{id}") // Maneja solicitudes DELETE a /asistencias/{id}
    public ResponseEntity<Void> deleteAsistencia(@PathVariable Integer id) { // Método para eliminar asistencia
        if (asistenciaRepository.existsById(id)) { // si existe
            asistenciaRepository.deleteById(id); // ELimina la asistencia
            return ResponseEntity.noContent().build(); // Devuelve 204 No content
        }
        return ResponseEntity.notFound().build(); // si no existe devuelve un 404
    }
}
