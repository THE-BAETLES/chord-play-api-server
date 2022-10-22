package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.item.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MyInformationResponse {
    private String id;
    private String nickname;

    private String username;

    private String firebaseUid;

    private String roles;


    private String email;
    private Country country;
    private Language language;
    private Gender gender;

    private PerformerGrade performerGrade;
    private Membership membership;

    private List<String> signupFavorite;

    private List<String> myCollection;

    public MyInformationResponse(User user){
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.username = user.getUsername();
        this.firebaseUid = user.getFirebaseUid();
        this.roles = user.getRoles();
        this.email = user.getEmail();
        this.country = user.getCountry();
        this.language = user.getLanguage();
        this.gender = user.getGender();
        this.performerGrade = user.getPerformerGrade();
        this.membership = user.getMembership();
        this.signupFavorite = user.getSignupFavorite();
        this.myCollection = user.getMyCollection();
    }
}
