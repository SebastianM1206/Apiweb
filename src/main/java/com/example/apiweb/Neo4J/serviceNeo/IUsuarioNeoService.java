package com.example.apiweb.Neo4J.serviceNeo;

import java.util.List;
import java.util.Optional;

import com.example.apiweb.Neo4J.modelNeo.UsuarioNeoModel;

public interface IUsuarioNeoService {
    List<UsuarioNeoModel> listarUsuarios();
    String crearUsuario(UsuarioNeoModel usuario);
    String eliminarUsuario(int id);
    Optional<UsuarioNeoModel> obtenerUsuarioPorId(int usuarioId);
    Optional<UsuarioNeoModel> actualizarUsuario(UsuarioNeoModel usuario);
}