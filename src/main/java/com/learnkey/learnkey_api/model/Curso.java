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
    private Long id_curso;
    private String nombre;
    private String descripcion;

    @Column(name = "id_administrador")
    private Long idAdministrador;

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
    private int horasTotales;

    public Curso() {
    }

    public Curso(String nombre, String descripcion, Long idAdministrador,
            String horaInicio, String horaFin, String fechaInicio,
            String fechaFin, String diasDeClase) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idAdministrador = idAdministrador;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.diasDeClase = diasDeClase;
        // No llamamos calcularHorasTotales aquí para evitar warning
    }

    // Getters y setters
    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
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

    public Long getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(Long idAdministrador) {
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

    public int getHorasTotales() {
        return horasTotales;
    }

    public void setHorasTotales(int horasTotales) {
        this.horasTotales = horasTotales;
    }

    // Método para calcular horas totales
    public void calcularHorasTotales() {
        try {
            String[] horaIni = this.horaInicio.split(":");
            String[] horaFinArr = this.horaFin.split(":");

            int inicioHoras = Integer.parseInt(horaIni[0]);
            int inicioMinutos = Integer.parseInt(horaIni[1]);

            int finHoras = Integer.parseInt(horaFinArr[0]);
            int finMinutos = Integer.parseInt(horaFinArr[1]);

            int horas = finHoras - inicioHoras;
            int minutos = finMinutos - inicioMinutos;

            if (minutos > 0) {
                horas += minutos / 60; // redondeamos a horas completas
            }

            this.horasTotales = horas;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            this.horasTotales = 0; // capturamos solo las excepciones necesarias
        }
    }
}
