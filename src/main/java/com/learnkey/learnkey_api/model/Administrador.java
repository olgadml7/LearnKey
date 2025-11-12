// Administrador.java (Model)
package com.learnkey.learnkey_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa la entidad Administrador en la base de datos.
 * Cada instancia de Administrador corresponde a un registro en la tabla
 * "administradores".
 */
@Entity
@Table(name = "administradores")
public class Administrador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_administrador; // Clave primaria

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellidos;

    @Column(length = 30)
    private String telefono;

    @Column(length = 30)
    private String email;

    @Column(length = 30)
    private String rol;

    public Administrador() {
    }

    public Administrador(String nombre, String apellidos, String telefono, String email, String rol) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
    }

    // Getters y Setters
    public Integer getId_administrador() {
        return id_administrador;
    }

    public void setId_administrador(Integer id_administrador) {
        this.id_administrador = id_administrador;
    }

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
