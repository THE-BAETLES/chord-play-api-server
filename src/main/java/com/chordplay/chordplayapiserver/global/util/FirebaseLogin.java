package com.chordplay.chordplayapiserver.global.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;


@Service
public class FirebaseLogin {

    private final FirebaseAuth firebaseAuth;
    private final String webApiKey;
    private final String adminUid;

    @Autowired
    public FirebaseLogin(FirebaseAuth firebaseAuth, @Value("${firebase.web-api-key}") String webApiKey, @Value("${firebase.admin-uid}") String adminUid) {
        this.firebaseAuth = firebaseAuth;
        this.webApiKey = webApiKey;
        this.adminUid = adminUid;
    }

    private final String url = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/verifyCustomToken";
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(3);
    public String getCustomTokenByUid(String uid){
        try {
            return firebaseAuth.createCustomToken(uid);
        } catch (FirebaseAuthException e) {
            throw new RuntimeException(e);
        }
    }

    public String getIdTokenByUid(String uid){

        String customToken = getCustomTokenByUid(uid);
        TokenRequest tokenRequest = TokenRequest.builder()
                .returnSecureToken(true)
                .token(customToken).build();
        TokenResponse tokenResponse = WebClient.create(url)
                .post()
                .uri(uriBuilder -> uriBuilder.path("")
                        .queryParam("key",webApiKey)
                        .build())
                .bodyValue(tokenRequest)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(TokenResponse.class)
                .block(REQUEST_TIMEOUT);
        return tokenResponse.getIdToken();
    }

    public String getAdminTokenByUid(){
        return getIdTokenByUid(adminUid);
    }
}
