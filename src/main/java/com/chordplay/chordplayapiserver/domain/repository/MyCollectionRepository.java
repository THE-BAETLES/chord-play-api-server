package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.MyCollection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyCollectionRepository extends MongoRepository<MyCollection,String> {
}
