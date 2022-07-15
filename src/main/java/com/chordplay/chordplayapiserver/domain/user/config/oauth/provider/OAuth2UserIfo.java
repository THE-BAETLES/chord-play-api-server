package com.chordplay.chordplayapiserver.domain.user.config.oauth.provider;

public interface OAuth2UserIfo {
    String getProviderId();
    String getProvider();
    String getEmail();
    String getName();
}
