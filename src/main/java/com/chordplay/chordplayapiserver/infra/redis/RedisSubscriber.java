package com.chordplay.chordplayapiserver.infra.redis;

import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusResponse;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;
    private final NotificationService notificationService;

    @Override
    public void onMessage(Message message, byte[] pattern) {//reids sub/pub
        try {
            AiStatusMessage aiStatusMessage = objectMapper.readValue(message.getBody(), AiStatusMessage.class);
            notificationService.sendStatusToClient(aiStatusMessage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
