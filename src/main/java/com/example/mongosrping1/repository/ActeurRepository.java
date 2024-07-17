package com.example.mongosrping1.repository;


import com.example.mongosrping1.model.Acteur;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActeurRepository extends MongoRepository<Acteur, Integer> {
/*    @Query("{ }")
    List<Acteur> findTous();*/
}