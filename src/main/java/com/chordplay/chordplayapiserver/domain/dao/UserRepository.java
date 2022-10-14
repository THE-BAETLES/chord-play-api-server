package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String>{
    public User findByUsername(String username);
    public User findByFirebaseUid(String FirebaseUid);
    public Boolean existsByNickname(String nickname);
    public User deleteByFirebaseUid(String FirebaseUid);
    @Update("{ '$push': { 'signup_favorite' : { $each : ?1 } } }")
    void findAndPushSignupFavoriteById(String id, List<String> signupFavorite);

    @Query("{ '_id' : ?0 }")
    @Update("{ '$addToSet': { 'my_collection' : ?1 } }")
    public void addVideoIdToCollectionById(String id, String videoId);

    @Query("{ '_id' : ?0 }")
    @Update("{ '$pull': { 'my_collection' : ?1 } }")
    public void deleteVideoIdFromCollectionById(String id, String videoId);
}
