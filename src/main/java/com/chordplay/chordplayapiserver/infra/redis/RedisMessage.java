package com.chordplay.chordplayapiserver.infra.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @ToString
@NoArgsConstructor
public class RedisMessage {
    private String status;

    public RedisMessage(String status) {
        this.status = status;
    }
}
