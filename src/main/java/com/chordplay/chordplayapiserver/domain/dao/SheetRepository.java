package com.chordplay.chordplayapiserver.domain.dao;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SheetRepository extends MongoRepository<Sheet, String> {
    Optional<Sheet> findOneByVideoId(String videoId);

    @Query(sort = "{ created_at : 1 }")
    List<Sheet> findAllByVideoId(String videoId);
    Sheet save(Sheet sheet);
}
