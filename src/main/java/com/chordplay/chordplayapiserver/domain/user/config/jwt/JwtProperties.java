package com.chordplay.chordplayapiserver.domain.user.config.jwt;

public interface JwtProperties {
    String SECRET = "askdjrkajse";
    int EXPIRATION_TIME = 60000 * 3600; // 하루
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String ID = "id";
    String USERNAME = "USERNAME";

}
