package com.chordplay.chordplayapiserver.domain.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.chordplay.chordplayapiserver.domain.entity.Sheet;

public interface SheetRepository extends MongoRepository<Sheet, String> {
}
