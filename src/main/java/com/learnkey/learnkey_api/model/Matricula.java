// Matricula.java (Model)
package com.learnkey.learnkey_api.model; // Paquete donde se encuentra la clase Matricula

import jakarta.persistence.Column; // Import para definir columnas de la tabla
import jakarta.persistence.Entity; // Import para marcar la clase como entidad JPA
import jakarta.persistence.GeneratedValue; // Import para generación automática de claves primarias
import jakarta.persistence.GenerationType; // Import para definir el tipo de generación de ID
import jakarta.persistence.Id; // Import para marcar el campo como clave primaria
import jakarta.persistence.Table; // Import para asignar la entidad a una tabla específica

/**
 * Clase que representa la entidad Matricula en la base de datos.
 * Cada instancia corresponde a la inscripción de un alumno en un curso.
 */
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "matriculas") // Asocia esta entidad con la tabla "matriculas" en la BD
public class Matricula {

    @Id // Define el campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID incremental
    private Integer id_matricula; // Clave primaria de la matrícula

    @Column(name = "id_alumno", nullable = false) // Columna obligatoria que referencia al alumno
    private Integer id_alumno; // ID del alumno matriculado (FK en la base de datos)

    @Column(name = "id_curso", nullable = false) // Columna obligatoria que referencia al curso
    private Integer id_curso; // ID del curso matriculado (FK en la base de datos)

    @Column(name = "fecha_alta") // Columna que guarda la fecha de alta/matrícula
    private String fecha_alta; // Fecha de inscripción al curso

    public Matricula() { // Constructor vacío requerido por JPA
    }

    public Matricula(Integer id_alumno, Integer id_curso, String fecha_alta) {
        this.id_alumno = id_alumno; // Asigna el ID del alumno
        this.id_curso = id_curso; // Asigna el ID del curso
        this.fecha_alta = fecha_alta; // Asigna la fecha de alta
    }

    // Getters y Setters
    public Integer getId_matricula() {
        return id_matricula; // Devuelve el ID de matrícula
    }

    public Integer getAlumnoId() {
        return id_alumno; // Devuelve el ID del alumno
    }

    public void setAlumnoId(Integer id_alumno) {
        this.id_alumno = id_alumno; // Establece el ID del alumno
    }

    public Integer getCursoId() {
        return id_curso; // Devuelve el ID del curso
    }

    public void setCursoId(Integer id_curso) {
        this.id_curso = id_curso; // Establece el ID del curso
    }

    public String getFecha_alta() {
        return fecha_alta; // Devuelve la fecha de alta
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta; // Establece la fecha de alta
    }
}
