package com.learnkey.learnkey_api.controller; //paquete donde se encuentra este controlador

import java.util.List; // clase para manejar listas de objetos
import java.util.Optional; // clase para manejar valores que pueden no estar presentes (null safe)

import org.springframework.beans.factory.annotation.Autowired; //le dice a Spring que inyecte automáticamente la dependencia (CursoRepository) para usarla sin tener que crearla manualmente.
import org.springframework.http.ResponseEntity; //permite devolver respuestas HTTP completas
import org.springframework.web.bind.annotation.CrossOrigin; // permite configurar permisos CORS
import org.springframework.web.bind.annotation.DeleteMapping; // mapea solicitudes DELETE
import org.springframework.web.bind.annotation.GetMapping; // mapea solicitudes GET
import org.springframework.web.bind.annotation.PathVariable; // obtiene variables desde la URL
import org.springframework.web.bind.annotation.PostMapping; // mapea solicitudes POST
import org.springframework.web.bind.annotation.PutMapping; // mapea solicitudes PUT
import org.springframework.web.bind.annotation.RequestBody; // indica que el cuerpo del request se convierte a un objeto Java
import org.springframework.web.bind.annotation.RequestMapping; //define la ruta base del controlador
import org.springframework.web.bind.annotation.RestController; //anotaciones que definen el comportamiento del controlador (explicado más abajo)

import com.learnkey.learnkey_api.model.Curso; // import del modelo Curso
import com.learnkey.learnkey_api.repository.CursoRepository; // import del repositorio que accede a la BD

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
    @GetMapping // se activa al hacer GET /cursos
    public List<Curso> getAllCursos() { // responde a GET/Cursos
        List<Curso> cursos = cursoRepository.findAll(); // devuelve todos los cursos de la base de datos
        cursos.forEach(c -> { // recorre cada curso
            if (c.getHorasTotales() == null) { // si las horas totales no están calculadas
                c.calcularHorasTotales(); // las calcula automáticamente
            }
        });
        return cursos; // devuelve la lista completa como json
    }

    // Obtener un curso por id
    @GetMapping("/{id}") // ruta: GET /cursos/{id}
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        Optional<Curso> curso = cursoRepository.findById(id); // busca el curso por su ID
        return curso.map(c -> { // si existe
            if (c.getHorasTotales() == null) { // valida si le faltan horas totales
                c.calcularHorasTotales(); // las calcula
            }
            return ResponseEntity.ok(c); // devuelve 200 OK + body JSON
        }).orElse(ResponseEntity.notFound().build()); // si no existe → 404 Not Found
    }

    // Crear un nuevo curso
    @PostMapping // responde a post/cursos
    public Curso createCurso(@RequestBody Curso curso) { // recibe un JSON y lo convierte a Curso
        if (curso.getHorasTotales() == null) { // si no trae horas totales
            curso.calcularHorasTotales(); // las calcula antes de guardar
        }
        return cursoRepository.save(curso); // guarda el curso en la base de datos y devuelve el objeto guardado
    }

    // Actualizar un curso existente
    @PutMapping("/{id}") // ruta: PUT /cursos/{id}
    public ResponseEntity<Curso> updateCurso(@PathVariable Integer id, @RequestBody Curso cursoDetails) {
        Optional<Curso> optionalCurso = cursoRepository.findById(id); // busca el curso
        if (optionalCurso.isPresent()) { // si existe
            Curso curso = optionalCurso.get(); // obtiene el objeto real
            // Actualiza todos los campos del curso con los datos recibidos
            curso.setNombre(cursoDetails.getNombre());
            curso.setDescripcion(cursoDetails.getDescripcion());
            curso.setIdAdministrador(cursoDetails.getIdAdministrador());
            curso.setHoraInicio(cursoDetails.getHoraInicio());
            curso.setHoraFin(cursoDetails.getHoraFin());
            curso.setFechaInicio(cursoDetails.getFechaInicio());
            curso.setFechaFin(cursoDetails.getFechaFin());
            curso.setDiasDeClase(cursoDetails.getDiasDeClase());

            curso.calcularHorasTotales(); // recalcular horas totales
            Curso updated = cursoRepository.save(curso); // guarda los cambios en BD
            return ResponseEntity.ok(updated); // devuelve 200 OK con el curso actualizado
        } else {
            return ResponseEntity.notFound().build(); // si no existe → 404
        }
    } // busca el curso por id, si existe actualiza todos los atributosy recalcula las
      // horas , y devuelve un ok con todo actualizado, sino da error

    // Eliminar un curso
    @DeleteMapping("/{id}") // ruta: DELETE /cursos/{id}
    public ResponseEntity<Void> deleteCurso(@PathVariable Integer id) {
        if (cursoRepository.existsById(id)) { // verifica si existe
            cursoRepository.deleteById(id); // elimina el curso
            return ResponseEntity.noContent().build(); // devuelve 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // si no existe → 404
        }
    }
}