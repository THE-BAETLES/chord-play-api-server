package com.chordplay.chordplayapiserver.domain.user.config;

import com.chordplay.chordplayapiserver.domain.user.TestUserRepository;
import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetails;
import com.chordplay.chordplayapiserver.domain.user.config.auth.PrincipalDetailsService;
import com.chordplay.chordplayapiserver.domain.user.config.firebase.FirebaseTokenFilter;
import com.chordplay.chordplayapiserver.domain.user.config.jwt.JwtAuthenticationFilter;
import com.chordplay.chordplayapiserver.domain.user.config.jwt.JwtAuthorizationFilter;
import com.chordplay.chordplayapiserver.domain.user.config.oauth.PrincipalOauth2UserService;
import com.google.firebase.auth.FirebaseAuth;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final TestUserRepository userRepository;
    private final PrincipalDetailsService principalDetailsService;
    private final FirebaseAuth firebaseAuth;
    private final CorsFilter corsFilter;
    @Bean
    public BCryptPasswordEncoder encoderPwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .addFilterBefore(new FirebaseTokenFilter(principalDetailsService, firebaseAuth), UsernamePasswordAuthenticationFilter.class)
                //.addFilter(new JwtAuthenticationFilter(authenticationManager()))  //파이어베이스 쪽 인증/인가 -> 자체 인가 작업 필요
                //.addFilter(new JwtAuthorizationFilter(authenticationManager(),userRepository))
                .authorizeRequests()
                .antMatchers("/api/v1/user/**")
                .access("hasRole('ROLE_USER') or hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/manager/**")
                .access("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
                .antMatchers("/api/v1/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .anyRequest().permitAll();



    }





}
