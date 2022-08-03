package com.chordplay.chordplayapiserver.global.sse.dao;

import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

public interface SseEmitterRepository {
    public CustomSseEmitter save(CustomSseEmitter value);
    public Boolean deleteById(String key);
    public List<CustomSseEmitter> findAllByVideoId(String videoId);
}
