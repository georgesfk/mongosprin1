package com.example.mongosrping1.repository;

import com.example.mongosrping1.model.Moy;
import com.example.mongosrping1.model.User;
import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.MongoTemplate;

@Repository
@Scope(scopeName = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AggregImpl  {

    @Autowired
    MongoTemplate mongoTemplate;

    @PostConstruct
    public void init() {
        if (mongoTemplate == null) {
            System.out.println("MongoTemplate is null!");
        } else {
            System.out.println("MongoTemplate is successfully injected.");
        }
    }

    public Double moyenne() {
        if (mongoTemplate == null) {
            System.out.println("MongoTemplate is null at moyenne()");
            throw new IllegalStateException("MongoTemplate is null");
        } else {
            System.out.println("MongoTemplate is not null at moyenne()");
        }

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("age").lt(40)),
                Aggregation.group().avg("age").as("moy"),
                Aggregation.project().andExclude("_id")
        );

        AggregationResults<Moy> aggregationResults = mongoTemplate.aggregate(agg, User.class, Moy.class);

        if (aggregationResults == null || aggregationResults.getMappedResults().isEmpty()) {
            System.out.println("Aggregation results are null or empty");
            throw new IllegalStateException("Aggregation results are null or empty");
        }

        Moy result = aggregationResults.getMappedResults().get(0);
        if (result == null) {
            System.out.println("Result is null");
            throw new IllegalStateException("Result is null");
        }

        System.out.println("Average age: " + result.getMoyenne());
        return result.getMoyenne();

    }}

