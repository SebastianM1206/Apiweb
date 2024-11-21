package com.example.apiweb.Neo4J.repositoryNeo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.apiweb.Neo4J.modelNeo.CursoNeoModel;

@Repository
public interface ICursoNeoRepository extends Neo4jRepository<CursoNeoModel, Integer> {
    // Define custom query methods if needed
}