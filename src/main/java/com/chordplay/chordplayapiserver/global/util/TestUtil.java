package com.chordplay.chordplayapiserver.global.util;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestUtil {

    private final PrincipalDetailsService principalDetailsService;
    /**
     * test 전용 강제 로그인
     */
    public void pushLoginById(String userId, String testString){
        if (testString.equals("test") == false) return;
        // User를 가져와 SecurityContext에 저장한다.
        UserDetails userDetails = principalDetailsService.loadUserById(userId);
        log.error(String.format("push login : %s - test 전용 function 입니다.",userId));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void pushLoginByUserName(String userName,String testString){
        if (testString.equals("test") == false) return;
        // User를 가져와 SecurityContext에 저장한다.
        UserDetails userDetails = principalDetailsService.loadUserByUsername(userName);
        log.error(String.format("push login : %s - test 전용 function 입니다.",userName));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
