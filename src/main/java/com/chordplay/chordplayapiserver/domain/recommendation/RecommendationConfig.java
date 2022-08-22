package com.chordplay.chordplayapiserver.domain.recommendation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RecommendationConfig {

    @Value("${recommendation.url}")
    String url;
    @Bean
    public WebClient webClient() {

        return WebClient.create(url);
    }
}
