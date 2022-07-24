package com.chordplay.chordplayapiserver.domain.user.dto;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.Video;
import com.chordplay.chordplayapiserver.domain.entity.item.Country;
import com.chordplay.chordplayapiserver.domain.entity.item.Gender;
import com.chordplay.chordplayapiserver.domain.entity.item.PerformerGrade;
import com.google.firebase.auth.FirebaseToken;
import lombok.*;

import java.util.List;

@ToString @Getter @Setter
@NoArgsConstructor
public class JoinRequest {
    //from token
    private String firebaseUid;
    private String username;
    private String email;

    //from body
    private Country country;
    private PerformerGrade performerGrade;
    private String nickname;
    private Gender gender;
    private List<Video> earlyFavoriteSongs;


    @Builder
    public JoinRequest(String firebaseUid, String username, String email, Country country, PerformerGrade performerGrade, String nickname, Gender gender, List<Video> earlyFavoriteSongs) {
        this.firebaseUid = firebaseUid;
        this.username = username;
        this.email = email;
        this.country = country;
        this.performerGrade = performerGrade;
        this.nickname = nickname;
        this.gender = gender;
        this.earlyFavoriteSongs = earlyFavoriteSongs;
    }



    public void setFirebaseJwtInfo(User user){
        this.firebaseUid = user.getFirebaseUid();
        this.username = user.getUsername();
        this.email = user.getEmail();
    }

    public User toEntity(String roles){
        return User.builder().username(username).email(email).firebaseUid(firebaseUid).roles(roles)
                .country(country).performerGrade(performerGrade).nickname(nickname).gender(gender)
                .earlyFavoriteSongs(earlyFavoriteSongs).build();
    }
}
