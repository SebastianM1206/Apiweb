package com.example.apiweb.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.apiweb.model.TutorModel;

import java.util.List;

public interface ITutorRepository extends MongoRepository<TutorModel, Integer> {

}
