package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SheetDataRepository extends MongoRepository<SheetData,String> {
    Optional<SheetData> findOneById(String id);
}
