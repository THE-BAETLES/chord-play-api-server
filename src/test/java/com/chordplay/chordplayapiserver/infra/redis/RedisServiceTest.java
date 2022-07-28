package com.chordplay.chordplayapiserver.infra.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RedisServiceTest {
    @Autowired RedisService redisService;

    @Test
    void test1(){
        ChannelTopic channelTopic = new ChannelTopic("ch01");
        RedisMessage redisMessage = new RedisMessage("hello");
        redisService.publish(channelTopic, redisMessage);
    }

}