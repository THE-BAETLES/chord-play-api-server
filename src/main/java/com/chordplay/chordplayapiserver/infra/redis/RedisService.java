    package com.chordplay.chordplayapiserver.infra.redis;


    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import lombok.RequiredArgsConstructor;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.listener.ChannelTopic;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class RedisService {
        private final RedisTemplate<String, String> redisTemplate;

        public void publish(ChannelTopic topic, RedisMessage message) throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            String value = objectMapper.writeValueAsString(message);
            redisTemplate.convertAndSend(topic.getTopic(), value);
        }


    }
