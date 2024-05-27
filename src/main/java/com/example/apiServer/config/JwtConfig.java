package com.example.apiServer.config;

import com.example.apiServer.jwt.JwtProperties;
import com.example.apiServer.jwt.TokenProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class JwtConfig {
    @Bean
    public TokenProvider tokenProvider(JwtProperties jwtProperties) {
        return new TokenProvider(jwtProperties.getSecret());
    }
}