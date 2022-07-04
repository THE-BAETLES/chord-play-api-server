package com.chordplay.chordplayapiserver.domain.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "USER_NICKNAME")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class UserNickname {
    @Id
    private String id;
    private String nickname;

    @Builder
    public UserNickname(String id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }
}
