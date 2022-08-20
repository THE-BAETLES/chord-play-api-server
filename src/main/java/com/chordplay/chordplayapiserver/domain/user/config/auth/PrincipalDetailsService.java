package com.chordplay.chordplayapiserver.domain.user.config.auth;

import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true)
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //시큐리티 session(내부 authentication(내부 UserDetails))로 들어감
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username: " + username);
        User user = userRepository.findByUsername(username);
        if (user != null){
            return new PrincipalDetails(user);
        }
        return null;
    }

    public UserDetails loadUserByFirebaseUid(String firebaseUid) {
        User user = userRepository.findByFirebaseUid(firebaseUid);
        if (user != null){
            return new PrincipalDetails(user);
        }
        return null;
    }

    public UserDetails loadUserById(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null){
            return new PrincipalDetails(user);
        }
        return null;
    }
}

