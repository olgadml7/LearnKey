package com.learnkey.learnkey_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_matricula;
    private Integer id_alumno;
    private Integer id_curso;
    private String fecha_alta;

    public Matricula() {
    }

    public Matricula(Integer id_alumno, Integer id_curso, String fecha_alta) {
        this.id_alumno = id_alumno;
        this.id_curso = id_curso;
        this.fecha_alta = fecha_alta;
    }

    // Getters y Setters
    public Integer getId_matricula() {
        return this.id_matricula;
    }

    public Integer getAlumnoId() {
        return this.id_alumno;
    }

    public void setAlumnoId(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Integer getCursoId() {
        return this.id_curso;
    }

    public void setCursoId(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getFecha_alta() {
        return this.fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
}
