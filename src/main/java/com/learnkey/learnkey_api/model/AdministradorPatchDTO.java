package com.learnkey.learnkey_api.model; // Nuevo paquete para DTOs, crea la carpeta dto si no existe

/**
 * 
 * DTO para modificaciones parciales de Administrador.
 * Se utiliza en el endpoint PATCH para actualizar solo los campos enviados.
 */
public class AdministradorPatchDTO {

    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private String rol;

    // Constructor vacío
    public AdministradorPatchDTO() {
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
