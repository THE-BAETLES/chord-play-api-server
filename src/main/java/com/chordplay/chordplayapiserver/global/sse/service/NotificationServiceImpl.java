package com.chordplay.chordplayapiserver.global.sse.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.chordplay.chordplayapiserver.global.sse.dao.SseEmitterRepository;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import com.chordplay.chordplayapiserver.infra.redis.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl implements NotificationService{

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 5;
    private final RedisUtil redisUtil;
    public SseEmitter subscribe(String userId, String videoId, String lastEventId) {

        CustomSseEmitter customSseEmitter = CustomSseEmitter.builder().videoId(videoId).userId(userId).timeout(DEFAULT_TIMEOUT).build();
        ContextUtil.getResponse().addHeader("access-control-allow-origin", "*"); //Cors 에러 방지를 위함
        sendToClient(customSseEmitter, "EventStream Created. [userId=" + userId + "]");
        return customSseEmitter;
    }

    public void sendStatusToClient(AiStatusMessage aiStatusMessage, CustomSseEmitter emitter){
        String videoId = emitter.getVideoId();
        sendToClient(emitter, aiStatusMessage);
        log.info(videoId+" message was sent to " + emitter.getUserId());
    }

    public void sendToClient(CustomSseEmitter emitter, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(emitter.getId())
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            emitter.completeWithError(exception);
            throw new RuntimeException("send error"); // 예외처리 추가
        }
    }

    public void publish(AiStatusMessage aiStatusMessage) throws JsonProcessingException {
        redisUtil.publish(new ChannelTopic("ch01"), aiStatusMessage);
    }
}
