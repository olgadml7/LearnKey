// Asistencia.java (Model)
package com.learnkey.learnkey_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entidad que representa la asistencia de un alumno en un curso.
 * Cada registro contiene la hora de entrada, salida y un token único generado
 * al escanear el QR.
 */
@Entity
@Table(name = "asistencia")
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_asistencia; // Clave primaria

    @Column(name = "id_alumno", nullable = false)
    private Integer id_alumno; // FK hacia Alumnos

    @Column(name = "id_curso", nullable = false)
    private Integer id_curso; // FK hacia Cursos

    @Column(name = "fecha")
    private String fecha; // Fecha del registro

    @Column(name = "hora_entrada")
    private String hora_entrada; // Hora de entrada

    @Column(name = "hora_salida")
    private String hora_salida; // Hora de salida

    @Column(name = "token")
    private String token; // Token único generado al escanear QR

    public Asistencia() {
    }

    public Asistencia(Integer id_alumno, Integer id_curso, String fecha, String hora_entrada, String hora_salida,
            String token) {
        this.id_alumno = id_alumno;
        this.id_curso = id_curso;
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.token = token;
    }

    // Getters y Setters
    public Integer getId_asistencia() {
        return id_asistencia;
    }

    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraEntrada() {
        return hora_entrada;
    }

    public void setHoraEntrada(String hora_entrada) {
        this.hora_entrada = hora_entrada;
    }

    public String getHoraSalida() {
        return hora_salida;
    }

    public void setHoraSalida(String hora_salida) {
        this.hora_salida = hora_salida;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
