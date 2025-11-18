// Asistencia.java (Model)
package com.learnkey.learnkey_api.model; // Paquete donde se encuentra la clase Asistencia dentro del proyecto

import jakarta.persistence.Column; // Importación para definir características personalizadas de columnas
import jakarta.persistence.Entity; // Importación para marcar esta clase como entidad JPA
import jakarta.persistence.GeneratedValue; // Importación para especificar generación automática de la clave primaria
import jakarta.persistence.GenerationType; // Importación para definir la estrategia de generación del ID
import jakarta.persistence.Id; // Importación para identificar el campo como clave primaria
import jakarta.persistence.Table; // Importación para mapear la clase a una tabla específica en la base de datos

/**
 * Entidad que representa la asistencia de un alumno en un curso.
 * Cada registro contiene la hora de entrada, salida y un token único generado
 * al escanear el QR.
 */
@Entity // Marca esta clase como una entidad persistente para JPA
@Table(name = "asistencia") // Define que esta clase se mapea a la tabla "asistencia"
public class Asistencia { // Declaración de la clase pública Asistencia

    @Id // Indica que este campo es la clave primaria de la entidad
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Estrategia para generar la clave primaria de forma
                                                        // autoincremental
    private Integer id_asistencia; // Campo que almacena el ID de la asistencia

    @Column(name = "id_alumno", nullable = false) // Columna obligatoria que representa la clave foránea a alumnos
    private Integer id_alumno; // FK hacia Alumnos // ID del alumno asociado a la asistencia

    @Column(name = "id_curso", nullable = false) // Columna obligatoria que representa la clave foránea a cursos
    private Integer id_curso; // FK hacia Cursos // ID del curso relacionado con esta asistencia

    @Column(name = "fecha") // Columna que almacena la fecha de la asistencia
    private String fecha; // Fecha del registro de asistencia

    @Column(name = "hora_entrada") // Columna que almacena la hora de entrada
    private String hora_entrada; // Hora en la que el alumno registro la entrada

    @Column(name = "hora_salida") // Columna que almacena la hora de salida
    private String hora_salida; // Hora en la que el alumno registro la salida

    @Column(name = "token") // Columna que almacena el token generado al escaner el codigo QR
    private String token; // Token único asociado a la operacion de registro

    public Asistencia() { // Constructo vacio requerido por JPA para crear instancias
    }

    public Asistencia(Integer id_alumno, Integer id_curso, String fecha, String hora_entrada, String hora_salida,
            String token) { // Constructor con todos los parámetros excepto el ID autogenerado
        this.id_alumno = id_alumno; // Asigna el ID del alumno al atributo
        this.id_curso = id_curso; // Asigna el ID del curso al atributo
        this.fecha = fecha; // Asigna la fecha al atributo
        this.hora_entrada = hora_entrada; // Asigna la hora de entrada al atributo
        this.hora_salida = hora_salida; // Asigna la hora de salida al atributo
        this.token = token; // Asigna el token único generado
    }

    // Getters y Setters

    public Integer getId_asistencia() { // Getter para obtener el ID de la asistencia
        return id_asistencia; // Devuelve el valor de id_asistencia
    }

    public Integer getId_alumno() { // Getter para obtener el ID del alumno
        return id_alumno; // Devuelve el valor de id_alumno
    }

    public void setId_alumno(Integer id_alumno) { // Setter para asignar un ID de alumno
        this.id_alumno = id_alumno; // Asigna el valor recibido a id_alumno
    }

    public Integer getId_curso() { // Getter para obtener el ID del curso
        return id_curso; // Devuelve el valor de id_curso
    }

    public void setId_curso(Integer id_curso) { // Setter para asignar un ID de curso
        this.id_curso = id_curso; // Asigna el ID recibido a id_curso
    }

    public String getFecha() { // Getter para obtener la fecha de la asistencia
        return fecha; // Devuelve la fecha almacenada
    }

    public void setFecha(String fecha) { // Setter para asignar la fecha de la asistencia
        this.fecha = fecha; // Asigna la fecha recibida
    }

    public String getHoraEntrada() { // Getter para obtener la hora de entrada
        return hora_entrada; // Devuelve la hora de entrada almacenada
    }

    public void setHoraEntrada(String hora_entrada) { // Setter para asignar la hora de entrada
        this.hora_entrada = hora_entrada; // Asigna la hora de entrada recibida
    }

    public String getHoraSalida() { // Getter para obtener la hora de salida
        return hora_salida; // Devuelve la hora de salida
    }

    public void setHoraSalida(String hora_salida) { // Setter para asignar la hora de salida
        this.hora_salida = hora_salida; // Modifica la hora de salida con el valor recibido
    }

    public String getToken() { // Getter para obtener el token único
        return token; // Devuelve el token almacenado
    }

    public void setToken(String token) { // Setter para modificar el token único
        this.token = token; // Asigna el token recibido
    }
}
