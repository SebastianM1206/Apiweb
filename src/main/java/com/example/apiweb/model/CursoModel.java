package com.example.apiweb.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Document(collection = "cursos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoModel {

    @Id
    private Integer curso_id;
    private String nombre_curso;
    private String modalidad;
    private String categoria;
    private Double costo;
    private Integer tutor_id;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Rating {
        private Integer usuario_id;
        private Double rating;
    }

    private List<Rating> ratings;
}
