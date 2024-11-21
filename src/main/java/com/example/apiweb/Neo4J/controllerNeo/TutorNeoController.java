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
import com.example.apiweb.Neo4J.modelNeo.TutorNeoModel;
import com.example.apiweb.Neo4J.serviceNeo.ITutorNeoService;
import com.example.apiweb.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/apiweb/v1/tutorNeo")
@CrossOrigin
public class TutorNeoController {

    @Autowired
    private ITutorNeoService tutorNeoService;

    // Crear un tutor en Neo4j
    @PostMapping("/")
    public ResponseEntity<String> crearTutorNeo(@RequestBody TutorNeoModel tutor) {
        tutorNeoService.crearTutor(tutor);
        return new ResponseEntity<>("Tutor creado correctamente en Neo4j.", HttpStatus.OK);
    }

    // Listar Tutores en Neo4j
    @GetMapping("/")
    public ResponseEntity<List<TutorNeoModel>> listarTutoresNeo() {
        List<TutorNeoModel> tutores = tutorNeoService.listarTutores();
        return new ResponseEntity<>(tutores, HttpStatus.OK);
    }

    // Consultar un tutor por Id en Neo4j 
    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorNeoModel> buscarTutorPorIdNeo(@PathVariable Integer tutorId) {
        TutorNeoModel tutor = tutorNeoService.obtenerTutorPorId(tutorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el tutor con el id " + tutorId));
        return ResponseEntity.ok(tutor);
    }

    // Actualizar un Tutor en Neo4j
    @PutMapping("/")
    public ResponseEntity<TutorNeoModel> actualizarTutorNeo(@RequestBody TutorNeoModel tutor) {
        TutorNeoModel tutorActualizado = tutorNeoService.actualizarTutor(tutor)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el tutor con el id " + tutor.getId_tutor()));
        return ResponseEntity.ok(tutorActualizado);
    }

    // Eliminar un Tutor por Id en Neo4j
    @DeleteMapping("/{tutorId}")
    public ResponseEntity<String> eliminarTutorPorIdNeo(@PathVariable Integer tutorId) {
        tutorNeoService.obtenerTutorPorId(tutorId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el tutor con el id " + tutorId));
        return new ResponseEntity<>(tutorNeoService.eliminarTutor(tutorId), HttpStatus.OK);
    }
}
