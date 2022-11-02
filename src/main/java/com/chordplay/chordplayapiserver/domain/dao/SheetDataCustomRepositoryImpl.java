package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import com.chordplay.chordplayapiserver.domain.entity.item.Chord;
import com.chordplay.chordplayapiserver.domain.entity.item.ChordInfo;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SheetDataCustomRepositoryImpl implements SheetDataCustomRepository{

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateSheetChord(String sheetId, int position, Chord chord) {
        //"{ '$set' : { 'infos.?1.chord' : ?2 } }"
        Query query = new Query(Criteria.where("_id").is(sheetId));
        //Update update = new Update().set(String.format("infos.%s", position), chord);
        Update update = new Update().set(String.format("infos.%s.root", position), chord.getRoot())
                .set(String.format("infos.%s.triad", position), chord.getTriad())
                .set(String.format("infos.%s.bass", position), chord.getBass());
        mongoTemplate.updateFirst(query,update,SheetData.class);
    }
}
