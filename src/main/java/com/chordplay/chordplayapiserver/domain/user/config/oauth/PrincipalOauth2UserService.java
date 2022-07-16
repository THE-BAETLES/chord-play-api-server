package com.chordplay.chordplayapiserver.domain.user.config.oauth;

import com.chordplay.chordplayapiserver.domain.user.TestUser;
import com.chordplay.chordplayapiserver.domain.user.TestUserRepository;
import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.config.oauth.provider.GoogleUserInfo;
import com.chordplay.chordplayapiserver.domain.user.config.oauth.provider.OAuth2UserIfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final TestUserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("getClientRegistration: " + userRequest.getClientRegistration());
        System.out.println("getAccessToken: " + userRequest.getAccessToken().getTokenValue());
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println("getAttributesL " + oAuth2User.getAttributes());

        OAuth2UserIfo oAuth2UserInfo = null;
        //회원가입 강제 진행
        if (userRequest.getClientRegistration().getRegistrationId().equals("google")){
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if (userRequest.getClientRegistration().getRegistrationId().equals("facebook")) {
            
        } else{
            System.out.println("I only support google and facebook now");
        }
        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = oAuth2UserInfo.getProviderId();
        String email = oAuth2UserInfo.getEmail();
        String username = provider + "_" + providerId;

        TestUser user = TestUser.builder()
                .provider(provider)
                .providerId(providerId)
                .email(email)
                .username(username).roles("ROLE_USER")
                .password("empty").build();
        TestUser newUser = userRepository.findByUsername(user.getUsername());

        if(newUser == null){
            userRepository.save(user);
        }
        return new PrincipalDetails(newUser, oAuth2User.getAttributes());
    }
}
