package com.chordplay.chordplayapiserver.domain.dao;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;

import java.util.Optional;

public interface SheetRepository extends MongoRepository<Sheet, String> {
    Optional<Sheet> findOneByVideoId(String videoId);
    Sheet save(Sheet sheet);
}
