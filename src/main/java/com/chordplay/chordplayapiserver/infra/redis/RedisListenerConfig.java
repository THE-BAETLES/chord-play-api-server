package com.chordplay.chordplayapiserver.infra.redis;

import com.chordplay.chordplayapiserver.domain.sheet.SheetRedisListener;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
@RequiredArgsConstructor
public class RedisListenerConfig {

    private RedisMessageListenerContainer container;
    private final RedisConnectionFactory redisConnectionFactory;
    @Bean
    RedisMessageListenerContainer redisContainer() {
        this.container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory);
        return container;
    }
}
