package com.example.apiweb.Neo4J.modelNeo;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Node("Tutor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorNeoModel {

    @Id
    private Long id_tutor;
    private String nombre_tutor;
}