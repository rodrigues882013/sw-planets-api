package com.application.swplanetsapi.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import com.mongodb.DB;

@Configuration
public class MongoConfig {

    private final MongoDbFactory mongo;

    @Autowired
    public MongoConfig(MongoDbFactory mongo) {
        this.mongo = mongo;
    }


}
