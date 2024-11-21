package com.example.apiweb.controller;

import com.example.apiweb.exception.CamposInvalidosException;
import com.example.apiweb.exception.RecursoNoEncontradoException;
import com.example.apiweb.model.CursoModel;
import com.example.apiweb.model.CursoModel.Rating;
import com.example.apiweb.service.ICursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/apiweb/v1/curso")
@CrossOrigin
public class CursoController {
    @Autowired
    private ICursoService cursoService;

    // Crear un curso
    @PostMapping("/")
    public ResponseEntity<String> crearCurso(@RequestBody CursoModel curso) {
        cursoService.crearCurso(curso);
        return new ResponseEntity<String>("Curso creado correctamente.",
                HttpStatus.OK);
    }

    // prueba de conexión a la base de datos
    @GetMapping("/test-db")
    public ResponseEntity<String> testDatabaseConnection() {
        try {
            List<CursoModel> cursos = cursoService.listarCursos();
            return new ResponseEntity<>(
                    "Conexión a la base de datos MongoDB exitosa. Número de cursos: " + cursos.size(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al conectar a la base de datos MongoDB: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Listar Cursos
    @GetMapping("/")
    public ResponseEntity<List<CursoModel>> listarCursos() {
        List<CursoModel> cursos = cursoService.listarCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    // Consultar un curso por Id
    @GetMapping("/{cursoId}")
    public ResponseEntity<CursoModel> buscarCursoPorId(@PathVariable Integer cursoId) {
        CursoModel curso = this.cursoService.obtenerCursoPorId(cursoId)
                .orElseThrow(
                        () -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));
        return ResponseEntity.ok(curso);
    }

    // Actualizar la información del curso que se desee
    @PutMapping("/{cursoId}")
    public ResponseEntity<String> actualizarCursoPorId(@PathVariable Integer cursoId,
            @RequestBody CursoModel detallesCurso) {

        CursoModel curso = this.cursoService.obtenerCursoPorId(cursoId)
                .orElseThrow(
                        () -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));

        // Actualizar solo los campos que no son nulos en detallesCurso, para poder
        // actualizar lo que le de la gana a uno
        if (detallesCurso.getNombre_curso() != null && !detallesCurso.getNombre_curso().isEmpty()) {
            curso.setNombre_curso(detallesCurso.getNombre_curso());
        }
        if (detallesCurso.getModalidad() != null && !detallesCurso.getModalidad().isEmpty()) {
            curso.setModalidad(detallesCurso.getModalidad());
        }
        if (detallesCurso.getCategoria() != null && !detallesCurso.getCategoria().isEmpty()) {
            curso.setCategoria(detallesCurso.getCategoria());
        }
        if (detallesCurso.getCosto() != null && detallesCurso.getCosto() >= 0) {
            curso.setCosto(detallesCurso.getCosto());
        }
        if (detallesCurso.getAsistentes() != null && detallesCurso.getAsistentes() >= 0) {
            curso.setAsistentes(detallesCurso.getAsistentes());
        }

        // Mensaje de éxito
        return new ResponseEntity<>(cursoService.actualizarCursoPorId(curso), HttpStatus.OK);
    }

    @PutMapping("/{cursoId}/rating")
    public ResponseEntity<String> agregarRatingACurso(@PathVariable Integer cursoId, @RequestBody Rating rating) {
        try {
            this.cursoService.agregarRatingACurso(cursoId, rating);
            return new ResponseEntity<>("Rating agregado con éxito al curso con id " + cursoId, HttpStatus.OK);
        } catch (RecursoNoEncontradoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un Curso por Id
    @DeleteMapping("/{cursoId}")
    public ResponseEntity<String> eliminarCursoPorId(@PathVariable Integer cursoId) {
        CursoModel curso = this.cursoService.obtenerCursoPorId(cursoId)
                .orElseThrow(
                        () -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));
        return new ResponseEntity<String>(cursoService.eliminarCursoPorId(cursoId), HttpStatus.OK);
    }

}