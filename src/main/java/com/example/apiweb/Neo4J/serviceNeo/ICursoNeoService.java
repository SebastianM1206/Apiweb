package com.example.apiweb.Neo4J.serviceNeo;
import com.example.apiweb.Neo4J.modelNeo.CursoNeoModel;
import com.example.apiweb.model.CursoModel;

import java.util.List;
import java.util.Optional;

public interface ICursoNeoService {
    List<CursoNeoModel> listarCursos();
    String crearCurso(CursoNeoModel curso);
    // CursoNeoModel actualizarCurso(Long id, CursoNeoModel curso);
    // void eliminarCurso(Long id);
    // Optional<CursoModel> obtenerCursoPorId(int cursoId);
}
