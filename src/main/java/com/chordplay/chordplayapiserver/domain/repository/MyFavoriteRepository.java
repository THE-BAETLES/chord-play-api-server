package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.MyFavorite;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyFavoriteRepository extends MongoRepository<MyFavorite,String> {
}
