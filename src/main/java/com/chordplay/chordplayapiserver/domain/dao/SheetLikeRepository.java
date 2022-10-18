package com.chordplay.chordplayapiserver.domain.dao;

import com.chordplay.chordplayapiserver.domain.entity.Sheet;
import com.chordplay.chordplayapiserver.domain.entity.SheetLike;
import com.chordplay.chordplayapiserver.domain.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SheetLikeRepository extends MongoRepository<SheetLike,String> {

    void deleteBySheetAndUser(Sheet sheet, User user);
}
