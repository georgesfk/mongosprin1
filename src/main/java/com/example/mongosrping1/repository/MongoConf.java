package com.example.mongosrping1.repository;

import com.example.mongosrping1.model.User;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class MongoConf {
    private String age;
    @Bean
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://user:pass@localhost:27017/");
    }

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory(MongoClient mongoClient) {
        return new SimpleMongoClientDatabaseFactory(mongoClient, "mongospring1data");
    }

    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDatabaseFactory) {
        return new MongoTemplate(mongoDatabaseFactory);
    }


/*    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return strings -> {

            userRepository.save(new User(1, "Calvin", "Ryan", 20 ));
            userRepository.save(new User(2, "Bryson", "Reid", 15 ));
            userRepository.save(new User(3, "Brad", "pitt", 10 ));
            userRepository.save(new User(4, "Robert", "DeNiro", 25 ));
        };
    }*/
}
