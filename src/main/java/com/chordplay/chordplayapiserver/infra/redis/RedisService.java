    package com.chordplay.chordplayapiserver.infra.redis;


    import lombok.RequiredArgsConstructor;
    import org.springframework.data.redis.core.RedisTemplate;
    import org.springframework.data.redis.listener.ChannelTopic;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class RedisService {
        private final RedisTemplate<String, Object> redisTemplate;

        public void publish(ChannelTopic topic, RedisMessage message){
            redisTemplate.convertAndSend(topic.getTopic(), message);
        }


    }
