package com.chordplay.chordplayapiserver.infra.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RedisSubscriber implements MessageListener {
    private final ObjectMapper objectMapper;

    //공유메모리...

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            RedisMessage data = objectMapper.readValue(message.getBody(), RedisMessage.class);
            System.out.println("message : " + data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
