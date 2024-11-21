package com.example.apiweb.Neo4J.repositoryNeo;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import com.example.apiweb.Neo4J.modelNeo.TutorNeoModel;

@Repository
public interface ITutorNeoRepository  extends  Neo4jRepository<TutorNeoModel, Integer>{
    
}
