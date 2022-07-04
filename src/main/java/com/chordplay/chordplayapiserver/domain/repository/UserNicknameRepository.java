package com.chordplay.chordplayapiserver.domain.repository;

import com.chordplay.chordplayapiserver.domain.entity.UserNickname;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserNicknameRepository extends MongoRepository<UserNickname, String> {
}
