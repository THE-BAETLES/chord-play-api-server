package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.video.dto.VideoRequest;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@NoArgsConstructor @Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SignupFavoriteRequest {

    List<VideoRequest> signupFavorite;

    public SignupFavoriteRequest(List<VideoRequest> signupFavorite) {
        this.signupFavorite = signupFavorite;
    }
}
