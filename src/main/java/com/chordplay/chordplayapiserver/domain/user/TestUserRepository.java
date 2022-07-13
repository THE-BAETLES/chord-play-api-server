package com.chordplay.chordplayapiserver.domain.user;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TestUserRepository extends MongoRepository<TestUser, String> {
    public TestUser findByUsername(String username);
}
