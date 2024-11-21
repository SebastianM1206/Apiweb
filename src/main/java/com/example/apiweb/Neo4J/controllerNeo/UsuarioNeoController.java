package com.example.apiweb.Neo4J.controllerNeo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.apiweb.Neo4J.modelNeo.UsuarioNeoModel;
import com.example.apiweb.Neo4J.serviceNeo.IUsuarioNeoService;
import com.example.apiweb.exception.RecursoNoEncontradoException;

@RestController
@RequestMapping("/apiweb/v1/usuarioNeo")
@CrossOrigin
public class UsuarioNeoController {

    @Autowired
    private IUsuarioNeoService usuarioNeoService;

    // Crear un usuario en Neo4j
    @PostMapping("/")
    public ResponseEntity<String> crearUsuarioNeo(@RequestBody UsuarioNeoModel usuario) {
        usuarioNeoService.crearUsuario(usuario);
        return new ResponseEntity<>("Usuario creado correctamente en Neo4j.", HttpStatus.OK);
    }

    // Listar Usuarios en Neo4j
    @GetMapping("/")
    public ResponseEntity<List<UsuarioNeoModel>> listarUsuariosNeo() {
        List<UsuarioNeoModel> usuarios = usuarioNeoService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // Consultar un usuario por Id en Neo4j 
    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioNeoModel> buscarUsuarioPorIdNeo(@PathVariable Integer usuarioId) {
        UsuarioNeoModel usuario = usuarioNeoService.obtenerUsuarioPorId(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el usuario con el id " + usuarioId));
        return ResponseEntity.ok(usuario);
    }

    // Actualizar un Usuario en Neo4j
    @PutMapping("/")
    public ResponseEntity<UsuarioNeoModel> actualizarUsuarioNeo(@RequestBody UsuarioNeoModel usuario) {
        UsuarioNeoModel usuarioActualizado = usuarioNeoService.actualizarUsuario(usuario)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el usuario con el id " + usuario.getUsuario_id()));
        return ResponseEntity.ok(usuarioActualizado);
    }

    // Eliminar un Usuario por Id en Neo4j
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> eliminarUsuarioPorIdNeo(@PathVariable Integer usuarioId) {
        usuarioNeoService.obtenerUsuarioPorId(usuarioId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Error! No se encontró el usuario con el id " + usuarioId));
        return new ResponseEntity<>(usuarioNeoService.eliminarUsuario(usuarioId), HttpStatus.OK);
    }
}
