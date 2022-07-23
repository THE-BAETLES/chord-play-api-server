package com.chordplay.chordplayapiserver.domain.user.config.firebase;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetailsService;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinTempSocialRequest;
import com.chordplay.chordplayapiserver.domain.user.service.UserService;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class FirebaseTokenFilter extends OncePerRequestFilter{


    private UserService userService;
    private PrincipalDetailsService principalDetailsService;
    private FirebaseAuth firebaseAuth;

    private static final List<String> EXCLUDE_URL =
            Collections.unmodifiableList(
                    Arrays.asList(
                            "/user/join"
                    ));

    @Builder
    public FirebaseTokenFilter(UserService userService, PrincipalDetailsService principalDetailsService, FirebaseAuth firebaseAuth) {
        this.userService = userService;
        this.principalDetailsService = principalDetailsService;
        this.firebaseAuth = firebaseAuth;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // get the token from the request
        FirebaseToken decodedToken;
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            setBadResponse(response, "INVALID_HEADER");
            return;
        }
        String token = header.substring(7);

        // verify IdToken
        try{
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            setBadResponse(response, "INVALID_TOKEN");
            return;
        }
        // User를 가져와 SecurityContext에 저장한다.
        UserDetails userDetails = null;
        try{
            userDetails = principalDetailsService.loadUserByFirebaseUid(decodedToken.getUid());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(NoSuchElementException e){
            userService.joinTempSocial(new JoinTempSocialRequest(decodedToken));
            setUnauthorizedResponse(response, "세부 회원가입이 필요합니다");
            return;
        }

        filterChain.doFilter(request, response);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return EXCLUDE_URL.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }

    private void setUnauthorizedResponse(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + msg + "\"}");
    }

    private void setBadResponse(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.SC_BAD_REQUEST);
        response.setContentType("application/json");
        response.getWriter().write("{\"message:\"" + msg + "\"}");
    }
}
