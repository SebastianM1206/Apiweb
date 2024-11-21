package com.example.apiweb.Neo4J.modelNeo;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node("Usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioNeoModel {

    @Id
    private Integer usuario_id;
    private String nombre_usuario;
    private String carrera;
    private Integer semestre;

}