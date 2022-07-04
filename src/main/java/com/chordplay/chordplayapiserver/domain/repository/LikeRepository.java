package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepository extends MongoRepository<Like,String> {
}
