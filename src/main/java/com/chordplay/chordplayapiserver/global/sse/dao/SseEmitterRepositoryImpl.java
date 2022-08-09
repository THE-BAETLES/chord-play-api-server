package com.chordplay.chordplayapiserver.global.sse.dao;

import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class SseEmitterRepositoryImpl implements SseEmitterRepository {

    Cache<String, CustomSseEmitter> cache = Caffeine.newBuilder()
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .maximumSize(10_000)
            .build();

    @Override
    public CustomSseEmitter save(CustomSseEmitter customSseEmitter) {
        cache.put(customSseEmitter.getId(), customSseEmitter);
        return customSseEmitter;
    }

    @Override
    public Boolean deleteById(String key) {
        cache.invalidate(key);
        return true;
    }

    @Override
    public List<CustomSseEmitter> findAllByVideoId(String videoId) {
        return cache.asMap().values().stream()
                .filter(value -> value.getVideoId().equals(videoId))
                .collect(Collectors.toList());
    }
}
