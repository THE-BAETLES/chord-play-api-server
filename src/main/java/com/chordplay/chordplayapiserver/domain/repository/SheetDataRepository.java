package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SheetDataRepository extends MongoRepository<SheetData,String> {
}
