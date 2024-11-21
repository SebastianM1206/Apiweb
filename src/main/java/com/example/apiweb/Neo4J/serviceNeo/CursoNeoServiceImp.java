package com.example.apiweb.Neo4J.serviceNeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.apiweb.Neo4J.modelNeo.CursoNeoModel;
import com.example.apiweb.Neo4J.repositoryNeo.ICursoNeoRepository;


@Service
@Primary
public class CursoNeoServiceImp implements ICursoNeoService {
    @Autowired
    ICursoNeoRepository cursoNeoRepository;

    @Override
    public String crearCurso(CursoNeoModel curso) {
        this.cursoNeoRepository.save(curso);
        return "El curso " + curso.getNombreCurso() + " fue creado exitosamente en Neo4J";
    }

    @Override
    public List<CursoNeoModel> listarCursos() {
        return this.cursoNeoRepository.findAll();
    }
    
}