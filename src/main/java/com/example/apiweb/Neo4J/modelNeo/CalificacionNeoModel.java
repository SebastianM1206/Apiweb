package com.example.apiweb.Neo4J.modelNeo;

import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RelationshipEntity(type = "califica")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalificacionNeoModel {

    @Id
    private Integer id;
    private Integer calificacion;

    @StartNode
    private UsuarioNeoModel usuario;

    @EndNode
    private CursoNeoModel curso;
}
