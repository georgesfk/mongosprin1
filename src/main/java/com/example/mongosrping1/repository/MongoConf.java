package com.example.mongosrping1.repository;

import com.example.mongosrping1.model.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoConf {
    private String age;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return strings -> {

            userRepository.save(new User(1, "Calvin", "Ryan", 20 ));
            userRepository.save(new User(2, "Bryson", "Reid", 15 ));
            userRepository.save(new User(3, "Toto", "Lolo", 10 ));
            userRepository.save(new User(4, "Hana", "Dish", 25 ));
        };
    }
}
