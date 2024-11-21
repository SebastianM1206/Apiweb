package com.example.apiweb.Neo4J.controllerNeo;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiweb.Neo4J.modelNeo.CursoNeoModel;
import com.example.apiweb.Neo4J.serviceNeo.ICursoNeoService;

@RestController
@RequestMapping("/apiweb/v1/cursoNeo")
@CrossOrigin
public class CursoNeoController {

    @Autowired
    private ICursoNeoService cursoNeoService;

    // Crear un curso en Neo4j
    @PostMapping("/")
    public ResponseEntity<String> crearCursoNeo(@RequestBody CursoNeoModel curso) {
        cursoNeoService.crearCurso(curso);
        return new ResponseEntity<>("Curso creado correctamente en Neo4j.", HttpStatus.OK);
    }

    // Listar Cursos en Neo4j
    @GetMapping("/")
    public ResponseEntity<List<CursoNeoModel>> listarCursosNeo() {
        List<CursoNeoModel> cursos = cursoNeoService.listarCursos();
        return new ResponseEntity<>(cursos, HttpStatus.OK);
    }

    // // Consultar un curso por Id en Neo4j
    // @GetMapping("/{cursoId}")
    // public ResponseEntity<CursoNeoModel> buscarCursoPorIdNeo(@PathVariable Integer cursoId) {
    //     CursoNeoModel curso = cursoNeoService.obtenerCursoPorId(cursoId)
    //             .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));
    //     return ResponseEntity.ok(curso);
    // }

    // // Actualizar la información del curso en Neo4j
    // @PutMapping("/{cursoId}")
    // public ResponseEntity<String> actualizarCursoPorIdNeo(@PathVariable Integer cursoId, @RequestBody CursoModel detallesCurso) {
    //     CursoNeoModel curso = cursoNeoService.obtenerCursoPorId(cursoId)
    //             .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));

    //     if (detallesCurso.getNombre_curso() != null && !detallesCurso.getNombre_curso().isEmpty()) {
    //         curso.setNombre_curso(detallesCurso.getNombre_curso());
    //     }
    //     if (detallesCurso.getModalidad() != null && !detallesCurso.getModalidad().isEmpty()) {
    //         curso.setModalidad(detallesCurso.getModalidad());
    //     }
    //     if (detallesCurso.getCategoria() != null && !detallesCurso.getCategoria().isEmpty()) {
    //         curso.setCategoria(detallesCurso.getCategoria());
    //     }
    //     if (detallesCurso.getCosto() != null && detallesCurso.getCosto() >= 0) {
    //         curso.setCosto(detallesCurso.getCosto());
    //     }

    //     return new ResponseEntity<>(cursoNeoService.actualizarCursoPorId(curso), HttpStatus.OK);
    // }

    // // Eliminar un Curso por Id en Neo4j
    // @DeleteMapping("/{cursoId}")
    // public ResponseEntity<String> eliminarCursoPorIdNeo(@PathVariable Integer cursoId) {
    //     CursoNeoModel curso = cursoNeoService.obtenerCursoPorId(cursoId)
    //             .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));
    //     return new ResponseEntity<>(cursoNeoService.eliminarCursoPorId(cursoId), HttpStatus.OK);
    // }
}