package com.example.apiweb.Neo4J.serviceNeo;

import java.util.List;
import java.util.Optional;

import com.example.apiweb.Neo4J.modelNeo.TutorNeoModel;

public interface ITutorNeoService {
    List<TutorNeoModel> listarTutores();
    String crearTutor(TutorNeoModel tutor);
    String eliminarTutor(int id);
    Optional<TutorNeoModel> obtenerTutorPorId(int tutorId);
    Optional<TutorNeoModel> actualizarTutor(TutorNeoModel tutor);
    
}