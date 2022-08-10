package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Video;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface VideoRepository extends MongoRepository<Video,String> {

    @Aggregation(pipeline = {
            "{ '$match': {'_id': ?0} }",
            "{ '$lookup': { 'from': 'SHEET',  'localField': '_id',  'foreignField': 'video.$id', 'as': 'sheets' } }",
            "{'$project': { '_id': 0, 'sheet_count': { '$size': '$sheets' } } }"
    })
    Long getSheetCount(String videoId);

    Optional<Video> findById(String id);

}
