package com.chordplay.chordplayapiserver.infra.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate redisTemplate;

    public <T> boolean saveData(String key, T data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String value = objectMapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch(Exception e){
            log.error(String.valueOf(e));
            return false;
        }
    }

    public <T> Optional<T> findByKey(String key, Class<T> classType) {
        String value = (String) redisTemplate.opsForValue().get(key);
        if(value == null){
            return Optional.empty();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return Optional.of(objectMapper.readValue(value, classType));
        } catch(Exception e){
            log.error(String.valueOf(e));
            return Optional.empty();
        }
    }
}
