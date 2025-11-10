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

import com.learnkey.learnkey_api.model.Administrador;
import com.learnkey.learnkey_api.repository.AdministradorRepository;

@RestController
@RequestMapping("/administradores")
@CrossOrigin(origins = "*")
public class AdministradorController {

    @Autowired
    private AdministradorRepository administradorRepository;

    // Listar todos los administradores
    @GetMapping
    public List<Administrador> getAllAdministradores() {
        return administradorRepository.findAll();
    }

    // Consultar administrador por id
    @GetMapping("/{id}")
    public Administrador getAdministradorById(@PathVariable Long id) {
        return administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con id: " + id));
    }

    // Consultar administrador por email
    @GetMapping("/email/{email}")
    public Administrador getAdministradorByEmail(@PathVariable String email) {
        return administradorRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con email: " + email));
    }

    // Crear nuevo administrador
    @PostMapping
    public Administrador createAdministrador(@RequestBody Administrador administrador) {
        return administradorRepository.save(administrador);
    }

    // Actualizar administrador
    @PutMapping("/{id}")
    public Administrador updateAdministrador(@PathVariable Long id, @RequestBody Administrador adminDetails) {
        Administrador admin = administradorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Administrador no encontrado con id: " + id));

        admin.setNombre(adminDetails.getNombre());
        admin.setApellidos(adminDetails.getApellidos());
        admin.setTelefono(adminDetails.getTelefono());
        admin.setEmail(adminDetails.getEmail());
        admin.setRol(adminDetails.getRol());

        return administradorRepository.save(admin);
    }

    // Eliminar administrador
    @DeleteMapping("/{id}")
    public String deleteAdministrador(@PathVariable Long id) {
        administradorRepository.deleteById(id);
        return "Administrador eliminado con id: " + id;
    }
}
