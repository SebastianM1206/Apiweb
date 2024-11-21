package com.example.apiweb.Neo4J.serviceNeo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.apiweb.Neo4J.modelNeo.TutorNeoModel;
import com.example.apiweb.Neo4J.repositoryNeo.ITutorNeoRepository;


@Service
@Primary
public class TutorNeoServiceImp implements ITutorNeoService {
    @Autowired
    ITutorNeoRepository tutorNeoRepository;

    @Override
    public String crearTutor(TutorNeoModel tutor) {
        this.tutorNeoRepository.save(tutor);
        return "El tutor " + tutor.getNombre_tutor() + " fue creado exitosamente en Neo4J";
    }

    @Override
    public List<TutorNeoModel> listarTutores() {
        return this.tutorNeoRepository.findAll();
    }

    @Override
    public Optional<TutorNeoModel> obtenerTutorPorId(int tutorId) {
        return this.tutorNeoRepository.findById(tutorId);
    }

    @Override
    public String eliminarTutor(int id) {
        Optional<TutorNeoModel> tutor = this.tutorNeoRepository.findById(id);
        if (tutor.isPresent()) {
            this.tutorNeoRepository.deleteById(id);
            return "El tutor con ID " + id + " fue eliminado exitosamente de Neo4J";
        } else {
            return "El tutor con ID " + id + " no fue encontrado en Neo4J";
        }
    }

    @Override
    public Optional<TutorNeoModel> actualizarTutor(TutorNeoModel tutor) {
        Optional<TutorNeoModel> tutorExistente = this.tutorNeoRepository.findById(tutor.getId_tutor());
        if (tutorExistente.isPresent()) {
            TutorNeoModel tutorActualizado = tutorExistente.get();
            tutorActualizado.setNombre_tutor(tutor.getNombre_tutor());
            this.tutorNeoRepository.save(tutorActualizado);
            return Optional.of(tutorActualizado);
        } else {
            return Optional.empty();
        }
    }
}
