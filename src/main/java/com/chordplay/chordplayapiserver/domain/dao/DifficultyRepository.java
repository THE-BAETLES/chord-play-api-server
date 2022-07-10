package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Difficulty;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DifficultyRepository extends MongoRepository<Difficulty, String> {
}
