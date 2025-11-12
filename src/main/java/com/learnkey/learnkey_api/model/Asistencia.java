package com.learnkey.learnkey_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "asistencia")
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_asistencia;
    private Integer id_alumno;
    private Integer id_curso;
    private String fecha;
    private String hora_entrada;
    private String hora_salida;
    private String token;

    public Asistencia() {
    }

    public Asistencia(Integer alumno, Integer curso, String fecha, String hora_entrada, String hora_salida,
            String token) {
        this.id_alumno = alumno;
        this.id_curso = curso;
        this.fecha = fecha;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
        this.token = token;
    }

    // Getters y Setters
    public Integer getId() {
        return this.id_asistencia;
    }

    public Integer getAlumnoId() {
        return this.id_alumno;
    }

    public Integer getCursoId() {
        return this.id_curso;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraEntrada() {
        return this.hora_entrada;
    }

    public void setHoraEntrada(String horaEntrada) {
        this.hora_entrada = horaEntrada;
    }

    public String getHoraSalida() {
        return this.hora_salida;
    }

    public void setHoraSalida(String horaSalida) {
        this.hora_salida = horaSalida;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
