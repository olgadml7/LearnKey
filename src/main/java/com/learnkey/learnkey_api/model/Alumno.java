package com.learnkey.learnkey_api.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumnos")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_alumno;
    private String nombre;
    private String apellidos;
    private String dni;
    private LocalDate fecha_nacimiento;
    private String email;
    private String telefono;

    public Alumno() {
    }

    public Alumno(Integer id_alumno, String nombre, String apellidos, String dni, LocalDate fecha_nacimiento,
            String email,
            String telefono) {
        this.id_alumno = id_alumno;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.email = email;
        this.telefono = telefono;
    }

    // Getters y Setters
    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
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

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
