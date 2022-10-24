package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.item.Chord;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetChangeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Optional;

public interface SheetDataRepository extends MongoRepository<SheetData,String>, SheetDataCustomRepository {
    Optional<SheetData> findOneById(String id);
}
