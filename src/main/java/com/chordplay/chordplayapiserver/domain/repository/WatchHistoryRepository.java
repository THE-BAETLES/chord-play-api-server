package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.WatchHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WatchHistoryRepository extends MongoRepository<WatchHistory,String> {
}
