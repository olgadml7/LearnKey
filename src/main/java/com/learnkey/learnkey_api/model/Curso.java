package com.learnkey.learnkey_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso") // nombre real en la tabla
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

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

    // Constructor vacío
    public Curso() {
    }

    // Getters y Setters
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
            if (horaInicio != null && horaFin != null && diasDeClase != null) {
                String[] hi = horaInicio.split(":");
                String[] hf = horaFin.split(":");
                int inicio = Integer.parseInt(hi[0]) * 60 + Integer.parseInt(hi[1]);
                int fin = Integer.parseInt(hf[0]) * 60 + Integer.parseInt(hf[1]);
                int duracion = fin - inicio; // en minutos
                if (duracion < 0)
                    duracion = 0;
                int dias = diasDeClase.split(",").length;
                this.horasTotales = (duracion * dias) / 60; // horas totales
            } else {
                this.horasTotales = 0;
            }
        } catch (NumberFormatException e) {
            this.horasTotales = 0;
        }
    }
}
