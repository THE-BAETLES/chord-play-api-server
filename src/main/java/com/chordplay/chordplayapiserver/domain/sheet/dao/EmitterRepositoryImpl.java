package com.chordplay.chordplayapiserver.domain.sheet.dao;

import com.chordplay.chordplayapiserver.infra.redis.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class EmitterRepositoryImpl implements EmitterRepository {

    private final RedisUtil redisUtil;
    private final RedisTemplate redisTemplate;

    @Override
    public SseEmitter save(String key, SseEmitter value) {
        Boolean result = redisUtil.setData(key, value);
        if (result == true) return value;
        else return null;
    }

    @Override
    public Boolean deleteById(String key) {
        return redisTemplate.delete(key);
    }
    @Override
    public Map<String, Object> findAllEventCacheStartWithId(String id) {
        Map<String, Object> eventCaches = new HashMap<String,Object>();
        RedisConnection redisConnection = null;
        try {
            redisConnection = redisTemplate.getConnectionFactory().getConnection();
            ScanOptions options = ScanOptions.scanOptions().match(id+"_*").count(100).build();

            Cursor c = redisConnection.scan(options);
            while (c.hasNext()) {
                String key = new String((byte[]) c.next());
                SseEmitter value = redisUtil.getData(key, SseEmitter.class).orElse(null);
                eventCaches.put(key, value);
                log.info("findAllStartWithId : " + key);
            }
        } finally {
            redisConnection.close();
            return eventCaches;
        }
    }
}
