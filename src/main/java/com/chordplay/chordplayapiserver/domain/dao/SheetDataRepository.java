package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetChangeRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.Optional;

public interface SheetDataRepository extends MongoRepository<SheetData,String> {
    Optional<SheetData> findOneById(String id);

    @Query("{ 'id' : ?0 , 'chordInfos.position' : ?1}")
    @Update("{ '$set' : { 'chordInfos.$.chord' : ?2 } }")
    public void updateSheetChord(String sheetId, int position, String chord);
}
