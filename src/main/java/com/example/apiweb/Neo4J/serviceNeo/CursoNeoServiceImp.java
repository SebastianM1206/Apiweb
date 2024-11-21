package com.example.apiweb.Neo4J.serviceNeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<CursoNeoModel> obtenerCursoPorId(int cursoId) {
        return this.cursoNeoRepository.findById(cursoId);
    }

    @Override
    public String eliminarCurso(int id) {
        Optional<CursoNeoModel> curso = this.cursoNeoRepository.findById(id);
        if (curso.isPresent()) {
            this.cursoNeoRepository.deleteById(id);
            return "El curso con ID " + id + " fue eliminado exitosamente de Neo4J";
        } else {
            return "El curso con ID " + id + " no fue encontrado en Neo4J";
        }
    }

    @Override
    public Optional<CursoNeoModel> actualizarCurso(CursoNeoModel curso) {
        Optional<CursoNeoModel> cursoExistente = this.cursoNeoRepository.findById(curso.getId_curso());
        if (cursoExistente.isPresent()) {
            CursoNeoModel cursoActualizado = cursoExistente.get();
            cursoActualizado.setNombreCurso(curso.getNombreCurso());
            cursoActualizado.setModalidad(curso.getModalidad());
            cursoActualizado.setCategoria(curso.getCategoria());
            cursoActualizado.setCosto(curso.getCosto());
            this.cursoNeoRepository.save(cursoActualizado);
            return Optional.of(cursoActualizado);
        } else {
            return Optional.empty();
        }
    }


    
}