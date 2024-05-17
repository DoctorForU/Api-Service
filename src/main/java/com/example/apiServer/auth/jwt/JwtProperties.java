package com.example.apiServer.auth.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private String header;

    @Value("${jwt.secret}")
    private String secret;
}