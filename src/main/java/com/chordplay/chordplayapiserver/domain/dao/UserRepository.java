package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUsername(String username);
    public User findByFirebaseUid(String FirebaseUid);
    public Boolean existsByNickname(String nickname);
    public User deleteByFirebaseUid(String FirebaseUid);

}
