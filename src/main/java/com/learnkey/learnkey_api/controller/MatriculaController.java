package com.learnkey.learnkey_api.controller; // Paquete donde se encuentra este controlador dentro del proyecto

import java.util.List; //Importa la interfaz List para manejar colecciones de matriculas
import java.util.Optional; //Optional se usa para evitar errores cuando un valor puede no existir (null)

import org.springframework.beans.factory.annotation.Autowired; //Permite la inyecciin automatica de dependencias, en este caso el repositorio
import org.springframework.http.ResponseEntity; //Permite devolver respuestas HTTP completas (codigo de estado + cuerpo )
import org.springframework.web.bind.annotation.CrossOrigin; //Permite solicitudes CORS desde otros origenes (ej : Angular)
import org.springframework.web.bind.annotation.DeleteMapping; //Indica que este metodo maneja solicitudes HTTP DELETE
import org.springframework.web.bind.annotation.GetMapping; //Indica un endpoint Get
import org.springframework.web.bind.annotation.PathVariable; // Permite recibir valores de la URL como parametros
import org.springframework.web.bind.annotation.PostMapping; //Indica un endpoint POST
import org.springframework.web.bind.annotation.PutMapping; //Indica un endpoint PUT
import org.springframework.web.bind.annotation.RequestBody; //Indica que los datos del cuerpo de la peticion deben mapearse a un objeto Java
import org.springframework.web.bind.annotation.RequestMapping; //Permite definir la ruta raiz del controlador
import org.springframework.web.bind.annotation.RestController; //Marca esta como un REST controller que devuelve JSON

import com.learnkey.learnkey_api.model.Matricula; //Importa el modelo Matricula para poder usarlo
import com.learnkey.learnkey_api.repository.MatriculaRepository; //Importa el repositorio que permite acceder a la base de datos

/**
 * Controlador REST para la gestión de matrículas.
 * Proporciona endpoints para CRUD de matrículas.
 */
@RestController // Indica que esta clase manejará peticiones REST y responderá en formato JSON
@RequestMapping("/matriculas") // Define la ruta base: todas las URLs empezarán con /matriculas
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para frontend Angular)
public class MatriculaController { // Inicio de la definición de la clase controladora

    @Autowired // Spring inyecta automáticamente el repositorio sin necesidad de instanciarlo
    private MatriculaRepository matriculaRepository; // Repositorio para realizar operaciones CRUD sobre la tabla
                                                     // matriculas

    /**
     * Obtiene todas las matrículas.
     *
     * @return Lista de matrículas
     */
    @GetMapping // Responde a peticiones GET en /matriculas
    public List<Matricula> getAllMatriculas() { // Método que devuelve una lista de todas las matrículas
        return matriculaRepository.findAll(); // Consulta a la base de datos que obtiene todos los registros
    }

    /**
     * Obtiene una matrícula por su id.
     *
     * @param id Identificador de la matrícula
     * @return Matrícula encontrada o 404 si no existe
     */
    @GetMapping("/{id}")
    public ResponseEntity<Matricula> getMatriculaById(@PathVariable Integer id) { // Obtiene una matrícula según el ID
                                                                                  // enviado en la URL
        Optional<Matricula> matricula = matriculaRepository.findById(id); // Busca la matrícula en la base de datos,
                                                                          // podría no existir
        return matricula.map(ResponseEntity::ok) // Si existe, devuelve HTTP 200 + la matrícula
                .orElse(ResponseEntity.notFound().build()); // Si no existe, devuelve HTTP 404 Not Found
    }

    /**
     * Crea una nueva matrícula.
     *
     * @param matricula Datos de la matrícula a crear
     * @return Matrícula creada
     */
    @PostMapping // Responde a POST /matriculas
    public Matricula createMatricula(@RequestBody Matricula matricula) { // Recibe los datos del cuerpo de la petición
                                                                         // para crear una matrícula nueva
        return matriculaRepository.save(matricula); // Guarda la nueva matrícula en la base de datos y devuelve el
                                                    // registro guardado
    }

    /**
     * Actualiza una matrícula existente.
     *
     * @param id               Identificador de la matrícula a actualizar
     * @param matriculaDetails Datos actualizados de la matrícula
     * @return Matrícula actualizada o 404 si no existe
     */
    @PutMapping("/{id}") // Responde a PUT /matriculas/{id}
    public ResponseEntity<Matricula> updateMatricula(@PathVariable Integer id,
            @RequestBody Matricula matriculaDetails) { // Recibe el ID y el objeto con los datos actualizados
        Optional<Matricula> optionalMatricula = matriculaRepository.findById(id); // Busca si la matrícula existe
        if (optionalMatricula.isPresent()) { // Si existe, se procede a actualizarla
            Matricula matricula = optionalMatricula.get(); // Obtiene el objeto existente
            matricula.setAlumnoId(matriculaDetails.getAlumnoId()); // Actualiza el ID del alumno
            matricula.setCursoId(matriculaDetails.getCursoId()); // Actualiza el ID del curso
            matricula.setFecha_alta(matriculaDetails.getFecha_alta()); // Actualiza la fecha de alta
            Matricula updated = matriculaRepository.save(matricula); // Guarda los cambios en la base de datos
            return ResponseEntity.ok(updated); // Devuelve HTTP 200 OK y la matrícula actualizada
        } else {
            return ResponseEntity.notFound().build(); // Devuelve HTTP 404 Not Found
        }
    }

    /**
     * Elimina una matrícula por su id.
     *
     * @param id Identificador de la matrícula a eliminar
     * @return 204 si se elimina, 404 si no existe
     */
    @DeleteMapping("/{id}") // Responde a DELETE /matriculas/{id}
    public ResponseEntity<Void> deleteMatricula(@PathVariable Integer id) { // Elimina una matrícula por su ID
        if (matriculaRepository.existsById(id)) { // Si la matrícula existe:
            matriculaRepository.deleteById(id); // La elimina de la base de datos
            return ResponseEntity.noContent().build(); // Devuelve HTTP 204 No Content
        } else { // Si la matrícula no existe:
            return ResponseEntity.notFound().build(); // Devuelve HTTP 404 Not Found
        }
    }
}
