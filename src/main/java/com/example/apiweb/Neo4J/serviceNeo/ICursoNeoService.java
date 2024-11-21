package com.example.apiweb.Neo4J.serviceNeo;
import java.util.List;
import java.util.Optional;

import com.example.apiweb.Neo4J.modelNeo.CursoNeoModel;

public interface ICursoNeoService {
    List<CursoNeoModel> listarCursos();
    String crearCurso(CursoNeoModel curso);
    String eliminarCurso(int id);
    Optional<CursoNeoModel> obtenerCursoPorId(int cursoId);
    Optional<CursoNeoModel> actualizarCurso(CursoNeoModel curso);
}
