package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SheetDataRepository extends MongoRepository<SheetData,String> {
}
