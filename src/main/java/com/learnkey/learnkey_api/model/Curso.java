package com.learnkey.learnkey_api.model; //cada clase model representa un objeto del mundo real, en este caso las tablas

import jakarta.persistence.Column; // Permite configurar las columnas de la tabla
import jakarta.persistence.Entity; // Marca esta clase como una entidad de base de datos
import jakarta.persistence.GeneratedValue; // Indica que el ID será generado automáticamente
import jakarta.persistence.GenerationType; // Determina la estrategia de generación del ID
import jakarta.persistence.Id; // Anota el campo como clave primaria
import jakarta.persistence.Table; // Permite definir el nombre exacto de la tabla

@Entity // convierte la clase en una tabla de la base de datos, marca esta clase como
        // Especifica el nombre de la tabla real en la BD.
@Table(name = "cursos") // define el nombre real de la tabla en la base de datos.
public class Curso { // declara la clase Curso. Todo lo que venga dentro define los atributos y
                     // comportamientos de un curso.

    @Id // Marca este atributo como clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // indica que la base de datos asigna automáticamente un valor
                                                        // único a la clave primaria.
    @Column(name = "id_curso") // indica a qué columna de la tabla corresponde cada atributo.
    private Integer id;

    @Column(name = "nombre") // indica el nombre exacto de la columna en la tabla.
    private String nombre; // tipo de dato y privada (solo accesible mediante métodos getter y setter).

    @Column(name = "descripcion") // Mapea la columna "descripcion"
    private String descripcion; // Breve descripción del curso

    @Column(name = "id_administrador") // ID del administrador responsable del curso
    private Integer idAdministrador;

    @Column(name = "hora_inicio") // Hora en formato HH:mm
    private String horaInicio;

    @Column(name = "hora_fin") // Hora de finalización del curso
    private String horaFin;

    @Column(name = "fecha_inicio") // Fecha en formato yyyy-MM-dd
    private String fechaInicio;

    @Column(name = "fecha_fin") // Fecha de finalización del curso
    private String fechaFin;

    @Column(name = "dias_de_clase") // Lista de días separados por comas (Ej: "L,M,X")
    private String diasDeClase;

    @Column(name = "horas_totales") // Total de horas calculadas automáticamente
    private Integer horasTotales;

    // Constructor vacío Java necesita un constructor vacío Spring y JPA lo
    // requieren para poder mapear automáticamente los datos de la base de datos a
    // objetos Java.
    public Curso() {
    }

    // Getters y Setters. Cada atributo tiene un getter (obtiene el valor) y un
    // setter (asigna un valor).
    public Integer getId() { // Devuelve el ID del curso
        return id;
    }

    public void setId(Integer id) { // Permite cambiar el ID (aunque normalmente no se usa)
        this.id = id;
    }

    public String getNombre() {
        return nombre; // Devuelve el nombre del curso
    }

    public void setNombre(String nombre) {
        this.nombre = nombre; // Asigna un nuevo nombre al curso
    }

    public String getDescripcion() { // Devuelve la descripción del curso
        return descripcion;
    }

    public void setDescripcion(String descripcion) { // Modifica la descripción del curso
        this.descripcion = descripcion;
    }

    public Integer getIdAdministrador() { // Devuelve el ID del administrador asociado
        return idAdministrador;
    }

    public void setIdAdministrador(Integer idAdministrador) { // Establece el administrador
        this.idAdministrador = idAdministrador;
    }

    public String getHoraInicio() { // Devuelve la hora de inicio
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) { // Asigna la hora de inicio
    }

    public String getHoraFin() { // Devuelve la hora de fin
        return horaFin;
    }

    public void setHoraFin(String horaFin) { // Asigna la hora de fin
        this.horaFin = horaFin;
    }

    public String getFechaInicio() { // Devuelve la fecha de inicio
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) { // Asigna la fecha de inicio
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() { // Devuelve la fecha final
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) { // Asigna la fecha final
        this.fechaFin = fechaFin;
    }

    public String getDiasDeClase() { // Devuelve los días de clase
        return diasDeClase;
    }

    public void setDiasDeClase(String diasDeClase) { // Asigna los días de clase
        this.diasDeClase = diasDeClase;
    }

    public Integer getHorasTotales() { // Devuelve el total de horas calculadas
        return horasTotales;
    }

    public void setHorasTotales(Integer horasTotales) { // Permite asignar manualmente horas totales
        this.horasTotales = horasTotales;
    }

    // Método para calcular horas totales automáticamente
    public void calcularHorasTotales() {
        try { // Protege el método ante errores de formato
            if (horaInicio != null && horaFin != null && diasDeClase != null) { // Comprueba que los valores existan
                String[] hi = horaInicio.split(":"); // Divide la hora de inicio en horas y minutos
                String[] hf = horaFin.split(":"); // Divide la hora de fin
                int inicio = Integer.parseInt(hi[0]) * 60 + Integer.parseInt(hi[1]); // Convierte a minutos totales
                int fin = Integer.parseInt(hf[0]) * 60 + Integer.parseInt(hf[1]);
                int duracion = fin - inicio; // en minutos
                if (duracion < 0) // si la duracion es negativa la pone a 0, evita errores
                    duracion = 0;
                int dias = diasDeClase.split(",").length; // cuenta los dias de clase
                this.horasTotales = (duracion * dias) / 60; // Convierte a horas totales y asigna
            } else {
                this.horasTotales = 0; // Si faltan datos, se asigna 0
            }
        } catch (NumberFormatException e) { // Si hay error con los números
            this.horasTotales = 0; // Se asigna 0 para evitar fallos
        }
    }
}
