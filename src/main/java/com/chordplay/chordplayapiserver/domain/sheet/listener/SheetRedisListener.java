package com.chordplay.chordplayapiserver.domain.sheet.listener;

import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;

@Slf4j
public class SheetRedisListener implements MessageListener {
    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;
    private CustomSseEmitter emitter;

    @Builder
    public SheetRedisListener(ObjectMapper objectMapper, NotificationService notificationService, CustomSseEmitter emitter) {
        this.objectMapper = objectMapper;
        this.notificationService = notificationService;
        this.emitter = emitter;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String status = objectMapper.readValue(message.getBody(), String.class);
            AiStatusMessage aiStatusMessage = new AiStatusMessage(status);
            log.info("ai status messgage : " + aiStatusMessage);
            notificationService.sendStatusToClient(aiStatusMessage,emitter);
            if (status.equals("3")){
                emitter.complete();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
