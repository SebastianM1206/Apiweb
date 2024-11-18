package com.example.apiweb.controller;

import com.example.apiweb.exception.CamposInvalidosException;
import com.example.apiweb.exception.RecursoNoEncontradoException;
import com.example.apiweb.model.CursoModel;
import com.example.apiweb.model.TutorModel;
import com.example.apiweb.service.ICursoService;
import com.example.apiweb.service.ITutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiweb/v1/tutor")
@CrossOrigin
public class TutorController {
    @Autowired
    private ITutorService tutorService;
    @Autowired
    private ICursoService cursoService;

    // Crear un tutor
    @PostMapping("/")
    public ResponseEntity<String> crearTutor(@RequestBody TutorModel tutor) {
        tutorService.crearTutor(tutor);
        return new ResponseEntity<String>("Tutor creado correctamente.", HttpStatus.OK);
    }

    // Listar tutores
    @GetMapping("/")
    public ResponseEntity<List<TutorModel>> listarTutores() {
        List<TutorModel> tutores = tutorService.listarTutores();
        return new ResponseEntity<>(tutores, HttpStatus.OK);
    }

    // Consultar un tutor por Id
    @GetMapping("/{tutorId}")
    public ResponseEntity<TutorModel> buscarTutorPorId(@PathVariable Integer tutorId) {
        TutorModel tutor = this.tutorService.obtenerTutorPorId(tutorId)
                .orElseThrow(
                        () -> new RecursoNoEncontradoException("Error! No se encontró el tutor con el id " + tutorId));
        return ResponseEntity.ok(tutor);
    }

    @PutMapping("/{tutorId}")
    public ResponseEntity<String> actualizarTutorPorId(@PathVariable Integer tutorId,
            @RequestBody TutorModel detallesTutor) {
        // Obtener el tutor actual por ID, o lanzar una excepción si no existe
        TutorModel tutor = this.tutorService.obtenerTutorPorId(tutorId)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "Error! No se encontró el tutor con el id " + tutorId));

        // Obtener los datos enviados en el JSON
        String nombreActualizar = detallesTutor.getNombre_tutor();
        List<Map<String, Object>> cursosActualizar = detallesTutor.getCursos();
        List<TutorModel.Calificacion> calificacionesActualizar = detallesTutor.getCalificaciones();

        // Validar que los campos no sean nulos o vacíos
        if ((nombreActualizar != null && !nombreActualizar.isEmpty()) ||
                (cursosActualizar != null && !cursosActualizar.isEmpty()) ||
                (calificacionesActualizar != null && !calificacionesActualizar.isEmpty())) {

            // Asignar valores actualizados si están presentes en el JSON
            if (nombreActualizar != null && !nombreActualizar.isEmpty()) {
                tutor.setNombre_tutor(nombreActualizar);
            }
            if (cursosActualizar != null && !cursosActualizar.isEmpty()) {
                tutor.setCursos(cursosActualizar);
            }
            if (calificacionesActualizar != null && !calificacionesActualizar.isEmpty()) {
                tutor.setCalificaciones(calificacionesActualizar);
            }

            // Guardar los cambios
            tutorService.actualizarTutorPorId(tutor);
            return new ResponseEntity<>("Tutor actualizado con éxito.", HttpStatus.OK);

        } else {
            throw new CamposInvalidosException("Error! Debes proporcionar al menos un campo para actualizar.");
        }
    }

    // Agregar un curso a un tutor
    @PostMapping("/{tutorId}/curso/{cursoId}")
    public ResponseEntity<String> agregarCursoATutor(@PathVariable Integer tutorId, @PathVariable Integer cursoId) {
        CursoModel curso = this.cursoService.obtenerCursoPorId(cursoId)
                .orElseThrow(
                        () -> new RecursoNoEncontradoException("Error! No se encontró el curso con el id " + cursoId));
        tutorService.agregarCursoATutor(tutorId, curso);
        return new ResponseEntity<String>("Curso agregado correctamente al tutor.", HttpStatus.OK);
    }

    // Eliminar un tutor por Id
    @DeleteMapping("/{tutorId}")
    public ResponseEntity<String> eliminarTutorPorId(@PathVariable Integer tutorId) {
        TutorModel tutor = this.tutorService.obtenerTutorPorId(tutorId)
                .orElseThrow(
                        () -> new RecursoNoEncontradoException("Error! No se encontró el tutor con el id " + tutorId));
        return new ResponseEntity<String>(tutorService.eliminarTutorPorId(tutorId), HttpStatus.OK);
    }

    @GetMapping("/tutoresCursosMAN/{ratings}")
    public ResponseEntity<List<TutorModel>> mostrarTutoresCursosMAN(@PathVariable Double ratings) {
        List<TutorModel> tutores = tutorService.mostrarTutoresCursosMayoresAN(ratings);
        return new ResponseEntity<>(tutores, HttpStatus.OK);
    }
}
