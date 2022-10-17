package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.SheetLike;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SheetLikeRepository extends MongoRepository<SheetLike,String> {
}
