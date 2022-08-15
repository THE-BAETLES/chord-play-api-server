package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WatchHistoryCustomRepositoryImpl implements WatchHistoryCustomRepository{

    private final MongoTemplate mongoTemplate;

    @Override
    public void UpdateCountAndTimeByUserAndVideo(User user, Video video) {
        Query query = new Query(Criteria.where("video").is(video).and("user").is(user));
        Update update = new Update().inc("play_count", 1).currentDate("last_played");
        mongoTemplate.update(WatchHistory.class).matching(query).apply(update).upsert();
    }
}
