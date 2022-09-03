package com.chordplay.chordplayapiserver.acceptance.global.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

@Service
@ActiveProfiles("test")
@RequiredArgsConstructor
public class DatabaseCleanup implements InitializingBean {

    private final MongoTemplate mongoTemplate;
    private List<String> collectionNames;

    @Override
    public void afterPropertiesSet() throws Exception {
        collectionNames = new ArrayList<>(mongoTemplate.getCollectionNames());
    }

    public void execute(){
        for(String name : collectionNames){
            if(name.equals("USER")){
                continue;
            }
            mongoTemplate.dropCollection(name);
        }
    }
}
