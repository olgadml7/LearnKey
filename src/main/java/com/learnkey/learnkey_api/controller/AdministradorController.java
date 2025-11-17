package com.learnkey.learnkey_api.controller; // Paquete donde se encuentra este controlador dentro del proyecto

import java.util.List; // Importa la clase List para manejar listas de objetos
import java.util.Optional; // Importa Optional para manejar valores que pueden ser nulos de forma segura

import org.springframework.beans.factory.annotation.Autowired; // Para inyectar dependencias automáticamente
import org.springframework.http.ResponseEntity; // Para construir respuestas HTTP personalizadas
import org.springframework.web.bind.annotation.CrossOrigin; // Para usar anotaciones REST: GetMapping, PostMapping, etc.
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnkey.learnkey_api.model.Administrador; // Importa el modelo Administrador
import com.learnkey.learnkey_api.repository.AdministradorRepository; // Importa el repositorio para acceder a BD

/**
 * Controlador REST para la gestión de administradores.
 * Proporciona endpoints para CRUD de administradores.
 */
@RestController // Indica que esta clase es un controlador REST
@RequestMapping("/administradores") // Prefijo de URL para todos los métodos del controlador
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (CORS)
public class AdministradorController { // Inicio de la clase del controlador

    @Autowired // Inyecta automáticamente el repositorio sin necesidad de instanciarlo
    private AdministradorRepository administradorRepository; // Repositorio para realizar operaciones CRUD

    /**
     * Obtiene la lista de todos los administradores.
     *
     * @return Lista de todos los administradores
     */
    @GetMapping // Maneja peticiones GET a /administradores
    public List<Administrador> getAllAdministradores() { // Método que devuelve todos los administradores
        return administradorRepository.findAll(); // Recupera la lista completa desde el repositorio
    }

    /**
     * Obtiene un administrador por su id.
     *
     * @param id Identificador del administrador
     * @return Administrador encontrado 204 o 404 si no existe te va a devolver
     */
    @GetMapping("/{id}") // Maneja peticiones GET a /administradores/{id}
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Integer id) { // Obtiene un admin por su ID
        Optional<Administrador> administrador = administradorRepository.findById(id); // Busca en BD usando Optional
        return administrador.map(ResponseEntity::ok) // Si existe, devuelve 200 OK con el admin
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve 404 Not Found
    }

    /**
     * Crea un nuevo administrador.
     *
     * @param administrador Datos del administrador a crear
     * @return Administrador creado
     */
    @PostMapping // Maneja peticiones POST a /administradores
    public Administrador createAdministrador(@RequestBody Administrador administrador) { // Crea un nuevo admin con
                                                                                         // datos del cuerpo
        return administradorRepository.save(administrador); // Guarda el administrador en la BD y lo devuelve
    }

    /**
     * Actualiza un administrador existente.
     *
     * @param id                   Identificador del administrador a actualizar
     * @param administradorDetails Datos actualizados del administrador
     * @return Administrador actualizado o 404 si no existe
     */
    @PutMapping("/{id}") // Maneja peticiones PUT a /administradores/{id}
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Integer id,
            @RequestBody Administrador administradorDetails) { // Recibe el ID y los datos actualizados
        Optional<Administrador> optionalAdministrador = administradorRepository.findById(id); // Busca el admin
                                                                                              // existente
        if (optionalAdministrador.isPresent()) { // Si existe el administrador
            Administrador administrador = optionalAdministrador.get(); // Obtiene el objeto existente
            administrador.setNombre(administradorDetails.getNombre()); // Actualiza el nombre
            administrador.setApellidos(administradorDetails.getApellidos()); // Actualiza los apellidos
            administrador.setEmail(administradorDetails.getEmail()); // Actualiza el email
            administrador.setTelefono(administradorDetails.getTelefono()); // Actualiza el teléfono
            Administrador updated = administradorRepository.save(administrador); // Guarda los cambios en BD
            return ResponseEntity.ok(updated); // Devuelve 200 OK con el administrador actualizado
        } else { // Si no existe el administrador
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        }
    }

    /**
     * Elimina un administrador por su id.
     *
     * @param id Identificador del administrador a eliminar
     * @return 204 si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}") // Maneja peticiones DELETE a /administradores/{id}
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Integer id) { // Elimina un admin por su ID
        if (administradorRepository.existsById(id)) { // Comprueba si existe antes de borrar
            administradorRepository.deleteById(id); // Elimina al administrador
            return ResponseEntity.noContent().build(); // Devuelve 204 No Content
        } else { // si no existe
            return ResponseEntity.notFound().build(); // Devuelve 404 Not Found
        }
    }
}
