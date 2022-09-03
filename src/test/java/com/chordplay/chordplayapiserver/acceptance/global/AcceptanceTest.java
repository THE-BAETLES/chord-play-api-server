package com.chordplay.chordplayapiserver.acceptance.global;

import com.chordplay.chordplayapiserver.acceptance.global.util.DatabaseCleanup;
import com.chordplay.chordplayapiserver.global.util.FirebaseUtil;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AcceptanceTest {
    @LocalServerPort
    int port;
    private static FirebaseUtil firebaseUtil;
    private static String firebaseToken;

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
    }

    public static String getFirebaseToken() {
        return firebaseToken;
    }
}
