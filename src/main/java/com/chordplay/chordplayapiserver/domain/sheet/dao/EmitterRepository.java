package com.chordplay.chordplayapiserver.domain.sheet.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

public interface EmitterRepository{

    public SseEmitter save(String key, SseEmitter value);
    public Boolean deleteById(String key);
    public Map<String, Object> findAllEventCacheStartWithId(String key);





}
