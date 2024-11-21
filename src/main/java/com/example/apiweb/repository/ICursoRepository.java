package com.example.apiweb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.apiweb.model.CursoModel;

public interface ICursoRepository extends MongoRepository<CursoModel, Integer> {
    @Aggregation(pipeline = {
            "{ '$match': { 'nombre_curso': ?0 } }",
            "{ '$lookup': { 'from': 'cursos', 'localField': 'categoria', 'foreignField': 'categoria', 'as': 'cursos_similares' } }",
            "{ '$unwind': '$cursos_similares' }",
            "{ '$match': { 'cursos_similares.nombre_curso': { '$ne': ?0 } } }",
            "{ '$addFields': { 'cursos_similares.promedio_rating': { '$avg': '$cursos_similares.ratings.rating' } } }",
            "{ '$sort': { 'cursos_similares.promedio_rating': -1 } }",
            "{ '$limit': 5 }",
            "{ '$replaceRoot': { 'newRoot': '$cursos_similares' } }"
    })
    List<CursoModel> findTop5BySimilarCategory(String nombreCurso);

    @Aggregation(pipeline = {
            "{ $match: { asistentes: { $gt: 0 } } }", // Filtra cursos con asistentes > 0
            "{ $sort: { asistentes: -1 } }", // Ordenar por asistentes descendente
            "{ $limit: 5 }" // Limita los resultados a 5 cursos
    })
    List<CursoModel> findTopCursosByAsistentes();

    @Aggregation(pipeline = {
            "{ $match: { asistentes: { $lt: 50 } } }", // Filtra cursos con pocos asistentes
            "{ $addFields: { avgRating: { $avg: '$ratings.rating' } } }", // Calcula el promedio de calificaciones
            "{ $match: { avgRating: { $lt: 3.0 } } }", // Filtra cursos con baja calificación
            "{ $sort: { avgRating: 1, asistentes: 1 } }", // Ordena por calificación y asistentes (ascendente)
            "{ $limit: 3 }" // Limita los resultados a lo que queramos mi fai
    })
    List<CursoModel> findCursosWithLowAttendanceAndRatings();
}
