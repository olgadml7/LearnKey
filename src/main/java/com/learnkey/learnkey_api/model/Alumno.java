// Alumno.java (Model)
package com.learnkey.learnkey_api.model; // Paquete donde se encuentra esta clase dentro de la estructura del proyecto

import java.time.LocalDate; // Importación de LocalDate para manejar fechas sin horas

import jakarta.persistence.Column; // Importación para personalizar columnas de la tabla
import jakarta.persistence.Entity; // Importación para marcar esta clase como entidad JPA
import jakarta.persistence.GeneratedValue; // Importación para definir generación automática de IDs
import jakarta.persistence.GenerationType; // Importación para especificar la estrategia de generación del ID
import jakarta.persistence.Id; // Importación para marcar el campo como clave primaria
import jakarta.persistence.Table; // Importación para mapear la entidad a una tabla específica

/**
 * Clase que representa la entidad Alumno en la base de datos.
 * Cada instancia de Alumno corresponde a un registro en la tabla "alumnos".
 */
@Entity // Indica que esta clase será una entidad persistente
@Table(name = "alumnos") // Especifica el nombre de la tabla en la base de datos
public class Alumno { // Declaración de la clase Alumno

    @Id // Marca este campo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Indica que el ID se generará automáticamente por la BD
    private Integer id_alumno; // Campo que almacena la clave primaria del alumno

    @Column(nullable = false, length = 100) // Columna con restricción NOT NULL y longitud máxima 100
    private String nombre; // Nombre del alumno

    @Column(nullable = false, length = 100) // Columna obligatoria con longitud máxima 100
    private String apellidos; // Apellidos del alumno

    @Column(nullable = false, length = 20, unique = true) // Columna obligatoria, longitud máxima 20 y valor único
    private String dni; // DNI del alumno, no puede repetirse

    @Column(name = "fecha_nacimiento") // Columna con nombre personalizado en la BD
    private LocalDate fecha_nacimiento; // Fecha de nacimiento del alumno

    @Column(length = 100) // Columna opcional con longitud máxima 100
    private String email; // Email del alumno

    @Column(length = 30) // Columna opcional, longitud máxima 30
    private String telefono; // Teléfono del alumno

    public Alumno() { // Constructor vacío requerido por JPA
    }

    public Alumno(Integer id, String nombre, String apellidos, String dni, LocalDate fecha_nacimiento,
            String email, String telefono) { // Constructor con parámetros para crear objetos más fácilmente
        this.id_alumno = id; // Asigna el parámetro al atributo id_alumno
        this.nombre = nombre; // Asigna el parámetro al atributo nombre
        this.apellidos = apellidos; // Asigna el parámetro al atributo apellidos
        this.dni = dni; // Asigna el parámetro al atributo dni
        this.fecha_nacimiento = fecha_nacimiento; // Asigna la fecha de nacimiento
        this.email = email; // Asigna el email
        this.telefono = telefono; // Asigna el teléfono
    }

    // Getters y Setters
    public Integer getId_alumno() { // Getter del ID del alumno
        return id_alumno; // Devuelve el valor del ID
    }

    public void setId_alumno(Integer id_alumno) { // Setter para modificar el ID del alumno
        this.id_alumno = id_alumno; // Asigna el nuevo valor del ID
    }

    public String getNombre() { // Getter del nombre
        return nombre; // Devuelve el nombre del alumno
    }

    public void setNombre(String nombre) { // Setter del nombre
        this.nombre = nombre; // Asigna el nombre recibido
    }

    public String getApellidos() { // Getter de los apellidos
        return apellidos; // Devuelve los apellidos
    }

    public void setApellidos(String apellidos) { // Setter de los apellidos
        this.apellidos = apellidos; // Asigna los apellidos recibidos
    }

    public String getDni() { // Getter del DNI
        return dni; // Devuelve el DNI
    }

    public void setDni(String dni) { // Setter del DNI
        this.dni = dni; // Asigna el nuevo DNI
    }

    public LocalDate getFecha_nacimiento() { // Getter de la fecha de nacimiento
        return fecha_nacimiento; // Devuelve la fecha de nacimiento
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) { // Setter de la fecha de nacimiento
        this.fecha_nacimiento = fecha_nacimiento; // Asigna la nueva fecha
    }

    public String getEmail() { // Getter del email
        return email; // Devuelve el email
    }

    public void setEmail(String email) { // Setter del email
        this.email = email; // Asigna el email recibido
    }

    public String getTelefono() { // Getter del teléfono
        return telefono; // Devuelve el número de teléfono
    }

    public void setTelefono(String telefono) { // Setter del teléfono
        this.telefono = telefono; // Asigna el teléfono recibido

    }
}
