package com.chordplay.chordplayapiserver.domain.chord.repository;

import com.chordplay.chordplayapiserver.domain.chord.entity.Chord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChordRepository extends JpaRepository<Chord, String> {

}