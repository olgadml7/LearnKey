// Matricula.java (Model)
package com.learnkey.learnkey_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase que representa la entidad Matricula en la base de datos.
 * Cada instancia corresponde a la inscripción de un alumno en un curso.
 */
@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_matricula; // Clave primaria

    @Column(name = "id_alumno", nullable = false)
    private Integer id_alumno; // FK hacia Alumnos

    @Column(name = "id_curso", nullable = false)
    private Integer id_curso; // FK hacia Cursos

    @Column(name = "fecha_alta")
    private String fecha_alta; // Fecha de inscripción

    public Matricula() {
    }

    public Matricula(Integer id_alumno, Integer id_curso, String fecha_alta) {
        this.id_alumno = id_alumno;
        this.id_curso = id_curso;
        this.fecha_alta = fecha_alta;
    }

    // Getters y Setters
    public Integer getId_matricula() {
        return id_matricula;
    }

    public Integer getAlumnoId() {
        return id_alumno;
    }

    public void setAlumnoId(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Integer getCursoId() {
        return id_curso;
    }

    public void setCursoId(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }
}
