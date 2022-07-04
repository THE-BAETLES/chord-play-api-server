package com.chordplay.chordplayapiserver.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "USER_NICKNAME")
@Getter
@RequiredArgsConstructor
public class UserNickname {
    @Id
    private String id;
    private String nickname;
}
