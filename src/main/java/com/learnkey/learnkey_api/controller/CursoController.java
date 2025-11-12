package com.learnkey.learnkey_api.controller;

import java.util.List; //clases de Java para manejar listas de objetos y valores que pueden no existir.
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; //le dice a Spring que inyecte automáticamente la dependencia (CursoRepository) para usarla sin tener que crearla manualmente.
import org.springframework.http.ResponseEntity; //permite devolver respuestas HTTP completas
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping; //anotaciones que definen el comportamiento del controlador (explicado más abajo)
import org.springframework.web.bind.annotation.RestController;

import com.learnkey.learnkey_api.model.Curso; //modelo
import com.learnkey.learnkey_api.repository.CursoRepository; //repositorio para acceder a la base de datos

@RestController // indica que esta clase responde a solicitudes HTTP y devuelve datos en formato
                // JSON
@RequestMapping("/cursos") // por lo que empezara todas las rutas de este controlador
@CrossOrigin(origins = "*") // permite cualquier origen pueda hacer solicitudes
public class CursoController { // definicion de la clase que maneja los cursos

    @Autowired
    private CursoRepository cursoRepository; // Inyecta automáticamente el repositorio de cursos, que se comunica con la
                                             // base de datos.Gracias a esto podemos hacer cosas como
                                             // cursoRepository.findAll() o cursoRepository.save(curso) sin tener que
                                             // escribir SQL.

    // Obtener todos los cursos
    @GetMapping
    public List<Curso> getAllCursos() { // responde a GET/Cursos
        List<Curso> cursos = cursoRepository.findAll(); // devuelve todos los cursos de la base de datos
        cursos.forEach(c -> {
            if (c.getHorasTotales() == null) {
                c.calcularHorasTotales();
            } // recorre la lista y si las horas totales es nulo, lo calcula automaticamente
        });
        return cursos; // devuelve la lista completa como json
    }

    // Obtener un curso por id
    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        Optional<Curso> curso = cursoRepository.findById(id); // maneja el caso de que el curso no exista
        return curso.map(c -> {
            if (c.getHorasTotales() == null) { // si existe calcula las horas y devuelve un 200 y sino un 404
                c.calcularHorasTotales();
            }
            return ResponseEntity.ok(c);
        }).orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo curso
    @PostMapping // responde a post/cursos
    public Curso createCurso(@RequestBody Curso curso) {
        if (curso.getHorasTotales() == null) {
            curso.calcularHorasTotales();
        }
        return cursoRepository.save(curso); // guarda el curso en la base de datos y devuelve el objeto guardado
    }

    // Actualizar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Integer id, @RequestBody Curso cursoDetails) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id);
        if (optionalCurso.isPresent()) {
            Curso curso = optionalCurso.get();
            curso.setNombre(cursoDetails.getNombre());
            curso.setDescripcion(cursoDetails.getDescripcion());
            curso.setIdAdministrador(cursoDetails.getIdAdministrador());
            curso.setHoraInicio(cursoDetails.getHoraInicio());
            curso.setHoraFin(cursoDetails.getHoraFin());
            curso.setFechaInicio(cursoDetails.getFechaInicio());
            curso.setFechaFin(cursoDetails.getFechaFin());
            curso.setDiasDeClase(cursoDetails.getDiasDeClase());
            // recalcular horas totales
            curso.calcularHorasTotales();
            Curso updated = cursoRepository.save(curso);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    } // busca el curso por id, si existe actualiza todos los atributosy recalcula las
      // horas , y devuelve un ok con todo actualizado, sino da error

    // Eliminar un curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        if (cursoRepository.existsById(id)) {
            cursoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    } // busca el curso por id, si existe lo elimina y sino devuelve un no encontrado
}// un 204 es que todo esta bien pero que no hay datos que devolver