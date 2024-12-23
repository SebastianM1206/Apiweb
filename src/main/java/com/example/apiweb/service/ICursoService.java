package com.example.apiweb.service;

import com.example.apiweb.model.CursoModel;
import com.example.apiweb.model.CursoModel.Rating;

import java.util.List;
import java.util.Optional;

public interface ICursoService {
    String crearCurso(CursoModel curso);

    List<CursoModel> listarCursos();

    Optional<CursoModel> obtenerCursoPorId(int cursoId);

    String eliminarCursoPorId(int cursoId);

    List<CursoModel> getTop5CursosBySimilarCategory(String nombreCurso);

    String actualizarCursoPorId(CursoModel curso);

    void agregarRatingACurso(int cursoId, Rating rating);

    List<CursoModel> getTopCursosByAsistentes();

    List<CursoModel> getCursosWithLowAttendanceAndRatings();

}
