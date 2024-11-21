package com.example.apiweb.Neo4J.serviceNeo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import com.example.apiweb.Neo4J.modelNeo.UsuarioNeoModel;
import com.example.apiweb.Neo4J.repositoryNeo.IUsuarioNeoRepository;

@Service
@Primary
public class UsuarioNeoServiceImp implements IUsuarioNeoService {
    @Autowired
    IUsuarioNeoRepository usuarioNeoRepository;

    @Override
    public String crearUsuario(UsuarioNeoModel usuario) {
        this.usuarioNeoRepository.save(usuario);
        return "El usuario " + usuario.getNombre_usuario() + " fue creado exitosamente en Neo4J";
    }

    @Override
    public List<UsuarioNeoModel> listarUsuarios() {
        return this.usuarioNeoRepository.findAll();
    }

    @Override
    public Optional<UsuarioNeoModel> obtenerUsuarioPorId(int usuarioId) {
        return this.usuarioNeoRepository.findById(usuarioId);
    }

    @Override
    public String eliminarUsuario(int id) {
        Optional<UsuarioNeoModel> usuario = this.usuarioNeoRepository.findById(id);
        if (usuario.isPresent()) {
            this.usuarioNeoRepository.deleteById(id);
            return "El usuario con ID " + id + " fue eliminado exitosamente de Neo4J";
        } else {
            return "El usuario con ID " + id + " no fue encontrado en Neo4J";
        }
    }

    @Override
    public Optional<UsuarioNeoModel> actualizarUsuario(UsuarioNeoModel usuario) {
        Optional<UsuarioNeoModel> usuarioExistente = this.usuarioNeoRepository.findById(usuario.getUsuario_id());
        if (usuarioExistente.isPresent()) {
            UsuarioNeoModel usuarioActualizado = usuarioExistente.get();
            usuarioActualizado.setNombre_usuario(usuario.getNombre_usuario());
            usuarioActualizado.setCarrera(usuario.getCarrera());
            usuarioActualizado.setSemestre(usuario.getSemestre());
            this.usuarioNeoRepository.save(usuarioActualizado);
            return Optional.of(usuarioActualizado);
        } else {
            return Optional.empty();
        }
    }
}