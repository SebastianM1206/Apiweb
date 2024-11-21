package com.example.apiweb.Neo4J.modelNeo;

import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Node("Curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoNeoModel { 

    @Id
    private Long id_curso;

    private String nombreCurso;
    private String modalidad;
    private String categoria;
    private Double costo;
}
