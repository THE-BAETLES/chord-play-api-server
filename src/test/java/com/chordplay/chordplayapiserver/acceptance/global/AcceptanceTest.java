package com.chordplay.chordplayapiserver.acceptance.global;

import com.chordplay.chordplayapiserver.acceptance.global.util.DatabaseCleanup;
import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.item.Country;
import com.chordplay.chordplayapiserver.domain.entity.item.Gender;
import com.chordplay.chordplayapiserver.domain.entity.item.PerformerGrade;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.global.util.FirebaseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class AcceptanceTest {
    @LocalServerPort
    int port;
    private static FirebaseUtil firebaseUtil;
    private static String firebaseToken;


    private String testVideoId = "dinia_m0HGE";
    private String testUserId;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    public void setFirebaseUtil(FirebaseUtil firebaseUtil) {
        AcceptanceTest.firebaseUtil = firebaseUtil;
    }

    public AcceptanceTest() {
    }
    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        RestAssured.basePath = "/v1";
        if (firebaseToken == null) firebaseToken = firebaseUtil.getAdminTokenByUid();
        databaseCleanup.execute();
        createAndSaveTestUser();
    }

    private void createAndSaveTestUser(){

        User user = User.builder()
                .firebaseUid(firebaseUtil.getAdminUid())
                .username("testUser")
                .email("testEmail@email.com").build();
        JoinRequest joinRequest = JoinRequest.builder()
                .country(Country.KR)
                .performerGrade(PerformerGrade.BEGINNER)
                .nickname("Chord Play")
                .gender(Gender.MALE)
                .signupFavorite(Arrays.asList("videoId1", "videoId2"))
                .build();
        joinRequest.setFirebaseJwtInfo(user);
        user = userRepository.save(joinRequest.toEntity("ROLE_USER"));
        this.setTestUserId(user.getId());
    }



    public static String getFirebaseToken() {
        return firebaseToken;
    }

    public String getTestVideoId() {
        return testVideoId;
    }

    public String getTestUserId() {
        return testUserId;
    }

    private void setTestUserId(String id) {
        this.testUserId = id;
    }

}
