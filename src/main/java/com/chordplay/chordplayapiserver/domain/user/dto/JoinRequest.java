package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.google.firebase.auth.FirebaseToken;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString @Getter @Setter
public class JoinRequest {
    //from token
    private String firebaseUid;
    private String username;
    private String email;

    //from body
    private String country;
    private String performer_grade;
    private String nickname;
    private String gender;
    private List<Video> earlyFavoriteSongs;

    @Builder
    public JoinRequest(String firebaseUid, String username, String email, String country, String performer_grade, String nickname, String gender, List<Video> earlyFavoriteSongs) {
        this.firebaseUid = firebaseUid;
        this.username = username;
        this.email = email;
        this.country = country;
        this.performer_grade = performer_grade;
        this.nickname = nickname;
        this.gender = gender;
        this.earlyFavoriteSongs = earlyFavoriteSongs;
    }

    public JoinRequest(FirebaseToken token) {
        this.username = token.getName();
        this.email = token.getEmail();
        this.firebaseUid = token.getUid();
    }

    public void setFirebaseJwtInfo(String firebaseUid, String username, String email){
        this.firebaseUid = firebaseUid;
        this.username = username;
        this.email = email;
    }

    public User toEntity(String roles){
        return User.builder().username(username).email(email).firebaseUid(firebaseUid).roles(roles)
                .country(country).performer_grade(performer_grade).nickname(nickname).gender(gender)
                .earlyFavoriteSongs(earlyFavoriteSongs).build();
    }
}
