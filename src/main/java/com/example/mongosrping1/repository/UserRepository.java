package com.example.mongosrping1.repository;


import com.example.mongosrping1.model.User;
import org.springframework.data.mongodb.repository.*;

import java.util.List;

public interface UserRepository extends MongoRepository<User, Integer> {
    @Query("{ 'age' : { $lt: ?0 } }")
    List<User> findPersonOlderThan(int age);
    @Query("{'lastnName' :  ?0}")
    @Update("{'$set' : {'age' :  34}}")
    void updateAge (String nom);


}