package com.chordplay.chordplayapiserver.infra.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate redisTemplate;

    public <T> boolean setData(String key, T data, long timeout, TimeUnit timeUnit) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String value = objectMapper.writeValueAsString(data);
            redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
            return true;
        } catch(Exception e){
            log.error(String.valueOf(e));
            return false;
        }
    }

    public <T> boolean setData(String key, T data) {
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

    public <T> Optional<T> getData(String key, Class<T> classType) {
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

    public Map<String, Object> findAllStartWithId(String matchString){
        Map<String, Object> datas = new HashMap<String,Object>();
        RedisConnection redisConnection = null;
        try {
            redisConnection = redisTemplate.getConnectionFactory().getConnection();
            ScanOptions options = ScanOptions.scanOptions().match(matchString+"_*").count(100).build();

            Cursor c = redisConnection.scan(options);
            while (c.hasNext()) {
                String key = new String((byte[]) c.next());
                String value = getData(key, String.class).orElse(null);
                datas.put(key, value);
                log.info("findAllStartWithId : " + key);
            }
        } finally {
            redisConnection.close();
            return datas;
        }
    }

    public void publish(ChannelTopic topic, Object message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String value = objectMapper.writeValueAsString(message);
        redisTemplate.convertAndSend(topic.getTopic(), value);
    }
}
