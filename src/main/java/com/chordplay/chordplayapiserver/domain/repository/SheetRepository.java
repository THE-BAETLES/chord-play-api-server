package com.chordplay.chordplayapiserver.domain.sheet.repository;

import com.chordplay.chordplayapiserver.domain.sheet.controller.SheetController;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface SheetRepository extends MongoRepository<SheetController, String> {
}
