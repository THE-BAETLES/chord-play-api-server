package com.chordplay.chordplayapiserver.util;

import com.chordplay.chordplayapiserver.domain.entity.User;
import com.chordplay.chordplayapiserver.domain.entity.item.*;
import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {

    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        User user = getAdminUser(annotation.hasNickname());
        UserDetails userDetails = new PrincipalDetails(user);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        context.setAuthentication(authentication);
        return context;
    }
    public static User getAdminUser(boolean hasNickname){
        if (hasNickname){
            return User.builder()
                    .id("6313b2381f8fa3bb122eaa78")
                    .username("최현준")
                    .email("test@gmail.com")
                    .nickname("test")
                    .roles("ROLE_USER")
                    .firebaseUid("firebase_uid")
                    .performerGrade(PerformerGrade.EXPERT)
                    .signupFavorite(Arrays.asList("videoId1", "videoId2"))
                    .myCollection(Arrays.asList("videoId3", "videoId4"))
                    .gender(Gender.MALE)
                    .country(Country.KR)
                    .language(Language.kr)
                    .membership(Membership.NORMAL)
                    .build();
        }

        return User.builder()
                .id("6313b2381f8fa3bb122eaa78")
                .firebaseUid("firebaseUid123123")
                .username("최현준")
                .email("test@gmail.com")
                .roles("ROLE_USER")
                .build();
    }
}