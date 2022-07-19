package com.chordplay.chordplayapiserver.domain.user.config.firebase;

import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetailsService;
import com.chordplay.chordplayapiserver.domain.user.dto.JoinRequest;
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
import java.util.NoSuchElementException;

public class FirebaseTokenFilter extends OncePerRequestFilter{


    private UserService userService;
    private PrincipalDetailsService principalDetailsService;
    private FirebaseAuth firebaseAuth;

    public FirebaseTokenFilter(PrincipalDetailsService principalDetailsService, FirebaseAuth firebaseAuth) {
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
            setUnauthorizedResponse(response, "INVALID_HEADER");
            return;
        }
        String token = header.substring(7);

        // verify IdToken
        try{
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            setUnauthorizedResponse(response, "INVALID_TOKEN");
            return;
        }
        // User를 가져와 SecurityContext에 저장한다.
        try{
            UserDetails user = principalDetailsService.loadUserByFirebaseUid(decodedToken.getUid());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch(NoSuchElementException e){
            userService.join(new JoinRequest(decodedToken));
            setOkResponse(response, "Join complete");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void setUnauthorizedResponse(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"" + msg + "\"}");
    }

    private void setOkResponse(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpStatus.SC_CREATED);
        response.setContentType("application/json");
        response.getWriter().write("{\"message:\"" + msg + "\"}");
    }
}
