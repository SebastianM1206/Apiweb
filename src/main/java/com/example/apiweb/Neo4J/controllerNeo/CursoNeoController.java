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
import com.example.apiweb.exception.RecursoNoEncontradoException;

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

    // Consultar un curso por Id en Neo4j 
    @GetMapping("/{cursoId}")
    public ResponseEntity<CursoNeoModel> buscarCursoPorIdNeo(@PathVariable Integer cursoId) {
        CursoNeoModel curso = cursoNeoService.obtenerCursoPorId(cursoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));
        return ResponseEntity.ok(curso);
    }

    // Actualizar un Curso en Neo4j
    @PutMapping("/")
    public ResponseEntity<CursoNeoModel> actualizarCursoNeo(@RequestBody CursoNeoModel curso) {
        CursoNeoModel cursoActualizado = cursoNeoService.actualizarCurso(curso)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + curso.getId_curso()));
        return ResponseEntity.ok(cursoActualizado);
    }

    // Eliminar un Curso por Id en Neo4j
    @DeleteMapping("/{cursoId}")
    public ResponseEntity<String> eliminarCursoPorIdNeo(@PathVariable Integer cursoId) {
        cursoNeoService.obtenerCursoPorId(cursoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));
        return new ResponseEntity<>(cursoNeoService.eliminarCurso(cursoId), HttpStatus.OK);
    }
}