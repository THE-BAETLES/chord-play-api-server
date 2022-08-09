package com.chordplay.chordplayapiserver.global.sse;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.sql.Timestamp;

@Getter
public class CustomSseEmitter extends SseEmitter {

    String videoId;
    String userId;
    String id;
    long currentTimeMillis = System.currentTimeMillis();

    @Builder
    public CustomSseEmitter(String videoId, String userId,Long timeout) {
        super(timeout);
        this.videoId = videoId;
        this.userId = userId;
        this.id = videoId + "/" + userId;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
