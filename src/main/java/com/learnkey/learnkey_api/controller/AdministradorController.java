package com.learnkey.learnkey_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.learnkey.learnkey_api.model.Administrador;
import com.learnkey.learnkey_api.repository.AdministradorRepository;

/**
 * Controlador REST para la gestión de administradores.
 * Proporciona endpoints para CRUD de administradores.
 */
@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    /**
     * Obtiene la lista de todos los administradores.
     *
     * @return Lista de todos los administradores
     */
    @GetMapping
    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    /**
     * Obtiene un administrador por su id.
     *
     * @param id Identificador del administrador
     * @return Administrador encontrado o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Administrador> getAdministradorById(@PathVariable Integer id) {
        Optional<Administrador> administrador = administradorRepository.findById(id);
        return administrador.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Crea un nuevo administrador.
     *
     * @param administrador Datos del administrador a crear
     * @return Administrador creado
     */
    @PostMapping
    public Administrador createAdministrador(@RequestBody Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    /**
     * Actualiza un administrador existente.
     *
     * @param id                   Identificador del administrador a actualizar
     * @param administradorDetails Datos actualizados del administrador
     * @return Administrador actualizado o 404 si no existe
     */
    @PutMapping("/{id}")
    public ResponseEntity<Administrador> updateAdministrador(@PathVariable Integer id,
            @RequestBody Administrador administradorDetails) {
        Optional<Administrador> optionalAdministrador = administradorRepository.findById(id);
        if (optionalAdministrador.isPresent()) {
            Administrador administrador = optionalAdministrador.get();
            administrador.setNombre(administradorDetails.getNombre());
            administrador.setApellidos(administradorDetails.getApellidos());
            administrador.setEmail(administradorDetails.getEmail());
            administrador.setTelefono(administradorDetails.getTelefono());
            Administrador updated = administradorRepository.save(administrador);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un administrador por su id.
     *
     * @param id Identificador del administrador a eliminar
     * @return 204 si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdministrador(@PathVariable Integer id) {
        if (administradorRepository.existsById(id)) {
            administradorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
