package com.learnkey.learnkey_api.model; //cada clase model representa un objeto del mundo real, en este caso las tablas

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // convierte la clase en una tabla de la base de datos, marca esta clase como
        // entidad que se guardará en la BD.
@Table(name = "cursos") // define el nombre real de la tabla en la base de datos.
public class Curso { // declara la clase Curso. Todo lo que venga dentro define los atributos y
                     // comportamientos de un curso.

    @Id // indica qué campo es la clave primaria.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que la base de datos asigna automáticamente un valor
                                                        // único a la clave primaria.
    @Column(name = "id_curso") // indica a qué columna de la tabla corresponde cada atributo.
    private Integer id;

    @Column(name = "nombre") // indica el nombre exacto de la columna en la tabla.
    private String nombre; // tipo de dato y privada (solo accesible mediante métodos getter y setter).

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "id_administrador")
    private Integer idAdministrador;

    @Column(name = "hora_inicio")
    private String horaInicio;

    @Column(name = "hora_fin")
    private String horaFin;

    @Column(name = "fecha_inicio")
    private String fechaInicio;

    @Column(name = "fecha_fin")
    private String fechaFin;

    @Column(name = "dias_de_clase")
    private String diasDeClase;

    @Column(name = "horas_totales")
    private Integer horasTotales;

    // Constructor vacío Java necesita un constructor vacío Spring y JPA lo
    // requieren para poder mapear automáticamente los datos de la base de datos a
    // objetos Java.
    public Curso() {
    }

    // Getters y Setters. Cada atributo tiene un getter (obtiene el valor) y un
    // setter (asigna un valor).
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDiasDeClase() {
        return diasDeClase;
    }

    public void setDiasDeClase(String diasDeClase) {
        this.diasDeClase = diasDeClase;
    }

    public Integer getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(Integer horasTotales) {
        this.horasTotales = horasTotales;
    }

    // Método para calcular horas totales automáticamente
    public void calcularHorasTotales() {
        try {
            if (horaInicio != null && horaFin != null && diasDeClase != null) { // verifica que no sean nulos
                String[] hi = horaInicio.split(":");
                String[] hf = horaFin.split(":");
                int inicio = Integer.parseInt(hi[0]) * 60 + Integer.parseInt(hi[1]); // divide la hora en horas y
                                                                                     // minutos
                int fin = Integer.parseInt(hf[0]) * 60 + Integer.parseInt(hf[1]);
                int duracion = fin - inicio; // en minutos
                if (duracion < 0) // si la duracion es negativa la pone a 0
                    duracion = 0;
                int dias = diasDeClase.split(",").length; // cuenta los dias de clase
                this.horasTotales = (duracion * dias) / 60; // horas totales
            } else {
                this.horasTotales = 0;
            }
        } catch (NumberFormatException e) {
            this.horasTotales = 0;
        } // si hay algun error de formato se asigna 0
    }
}
