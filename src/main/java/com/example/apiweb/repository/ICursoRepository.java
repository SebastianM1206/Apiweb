package com.example.apiweb.repository;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.apiweb.model.CursoModel;

import java.util.List;

public interface ICursoRepository extends MongoRepository<CursoModel, Integer> {
    @Aggregation(pipeline = {
            "{ $match: { categoria: ?0 } }",
            "{ $addFields: { calificacionPromedio: { $avg: '$ratings.rating' } } }",
            "{ $sort: { calificacionPromedio: -1 } }",
            "{ $limit: 5 }"
    })
    List<CursoModel> findTopCursosPorCategoria(String categoria);

    List<CursoModel> listarCursosRatingsMayoresAN(Double ratings);
}
