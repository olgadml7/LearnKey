// Administrador.java (Model)
package com.learnkey.learnkey_api.model; //Define el paquete al que pertenece esta clase dentro del proyecto 

import jakarta.persistence.Column; // Importa la anotación @Column para configurar propiedades de columnas en la tabla
import jakarta.persistence.Entity; // Importa @Entity para indicar que esta clase representa una tabla de base de datos
import jakarta.persistence.GeneratedValue; // Permite indicar que un campo tendrá un valor generado automáticamente
import jakarta.persistence.GenerationType; // Define la estrategia de generación del ID (IDENTITY, AUTO, etc.)
import jakarta.persistence.Id; // Marca un campo como clave primaria de la tabla
import jakarta.persistence.Table; // Permite indicar el nombre de la tabla en base de datos

/**
 * Clase que representa la entidad Administrador en la base de datos.
 * Cada instancia de Administrador corresponde a un registro en la tabla
 * "administradores".
 */
@Entity // Indica que esta clase es una entidad JPA y se mapeará a una tabla

@Table(name = "administradores") // Especifica que esta entidad se mapeará a la tabla llamada "administradores"
public class Administrador { // Inicio de la definición de la clase Administrador

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_administrador; // Clave primaria
    // Campo que representa el ID del administrador, entero y autogenerado

    @Column(nullable = false, length = 100) // Indica que la columna no puede ser nula y tendrá un máximo de 100
                                            // caracteres
    private String nombre; // Nombre del administrador

    @Column(nullable = false, length = 100) // Campo obligatorio, máximo 100 caracteres
    private String apellidos; // Apellidos del administrador

    @Column(length = 30) // Campo opcional, máximo 30 caracteres
    private String telefono; // Teléfono del administrador (opcional)

    @Column(length = 30) // Campo opcional con máximo 30 caracteres
    private String email; // Email del administrador (opcional)

    @Column(length = 30) // Campo opcional para guardar el rol (ej: director, profesor, admin)

    private String rol; // Rol del administrador dentro del sistema

    public Administrador() { // Constructor vacío requerido por JPA para poder instanciar la clase
    }

    public Administrador(String nombre, String apellidos, String telefono, String email, String rol) { // Constructor
                                                                                                       // con parámetros
                                                                                                       // para crear
                                                                                                       // administradores
                                                                                                       // fácilmente
        this.nombre = nombre; // Asigna el nombre recibido al atributo de la clase
        this.apellidos = apellidos; // Asigna los apellidos
        this.telefono = telefono; // Asigna el teléfono
        this.email = email; // Asigna el email
        this.rol = rol; // Asigna el rol
    }

    // Getters y Setters
    // Métodos para acceder y modificar los atributos privados de la clase
    public Integer getId_administrador() {
        return id_administrador;
        // Devuelve el ID del administrador
    }

    public void setId_administrador(Integer id_administrador) {
        this.id_administrador = id_administrador;
        // Asigna un nuevo valor al ID
    }

    public String getNombre() {
        return nombre;
        // Devuelve el nombre
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        // Modifica el nombre
    }

    public String getApellidos() {
        return apellidos;
        // Devuelve los apellidos
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
        // Asigna nuevos apellidos
    }

    public String getTelefono() {
        return telefono;
        // Devuelve el telefono
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;

        // Modifica el telefono
    }

    public String getEmail() {
        return email;
        // Devuelve el email

    }

    public void setEmail(String email) {
        this.email = email;
        // Modifica el email
    }

    public String getRol() {
        return rol;
        // Devuelve el rol
    }

    public void setRol(String rol) {
        this.rol = rol;
        // Modifica el rol
    }
}
