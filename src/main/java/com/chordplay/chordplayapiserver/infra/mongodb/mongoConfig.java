package com.chordplay.chordplayapiserver.infra.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.test.annotation.Rollback;

@Configuration
public class mongoConfig {
    @Value("${spring.data.mongodb.uri}")
    private String mongodbUri;

    @Bean
    public MongoDatabaseFactory mongoDatabaseFactory() {
        return new SimpleMongoClientDatabaseFactory(mongodbUri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
            return new MongoTemplate(mongoDatabaseFactory());
        }

    @Bean
    public MongoTransactionManager mongoTransactionManager() {
        return new MongoTransactionManager(mongoDatabaseFactory());
    }

}
