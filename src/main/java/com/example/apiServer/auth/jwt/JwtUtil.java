package com.example.apiServer.auth.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;

import static org.springframework.security.config.Elements.JWT;

@Component
@RequiredArgsConstructor
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;
    private int expirationTimeSeconds = 60;
    @Value("${jwt.refresh-token-expiration-seconds}")
    private int refreshTokenExpirationSeconds;
    private String tokenPrefix = "Bearer ";
    private ObjectMapper objectMapper = new ObjectMapper();

    // 인증 헤더에 토큰 접두사가 포함되어 있는지 확인
    public boolean isIncludeTokenPrefix(String authorization) {
        return authorization.split(" ")[0].equals(tokenPrefix.trim());
    }

    // 인증 헤더에서 실제 토큰을 추출
    public String extractTokenFromAuthorization(String authorization) {
        return authorization.replace(tokenPrefix, "");
    }

    // 사용자 정보를 바탕으로 JWT 생성
    public String createAuthorization(LoggedInUser loggedInUser, Instant current) {
        String token = createAccessToken(loggedInUser, current);
        return tokenPrefix.concat(token);
    }

    // 토큰이 만료되었는지 확인
    public boolean isTokenExpired(String token) {
        Instant expiredAt = JWT.decode(token)
                .getExpiresAtAsInstant();
        return expiredAt.isBefore(Instant.now());
    }

    // 토큰이 변조되지 않았는지 확인
    public boolean isTokenNotManipulated(String token) {
        try {
            JWT.require(Algorithm.HMAC512(secret))
                    .build().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    // 토큰에서 사용자 정보 추출
    public LoggedInUser extractUserFromToken(String token) {
        String payload = JWT.decode(token)
                .getPayload();

        byte[] decodedBytes = Base64.getDecoder().decode(payload);
        String decodedPayload = new String(decodedBytes);

        return parseUserFromJwt(decodedPayload);
    }

    // 사용자 정보를 바탕으로 액세스 토큰 생성
    public String createAccessToken(LoggedInUser loggedInUser, Instant current) {
        return JWT.create()
                .withSubject(loggedInUser.getEmail())
                .withExpiresAt(current.plusSeconds(expirationTimeSeconds))
                .withClaim("email", loggedInUser.getEmail())
                .withClaim("username", loggedInUser.getUsername())
                .sign(Algorithm.HMAC512(secret));
    }

    // 리프레시 토큰 생성
    public String createRefreshToken(String userId, Instant current) {
        return JWT.create()
                .withExpiresAt(current.plusSeconds(refreshTokenExpirationSeconds))
                .withClaim("id", id)
                .sign(Algorithm.HMAC512(secret));
    }

    // JWT 페이로드에서 사용자 정보 파싱
    private LoggedInUser parseUserFromJwt(String decodedPayload) {
        try {
            LinkedHashMap<String, Object> payloadMap = objectMapper.readValue(decodedPayload, LinkedHashMap.class);
            String email = (String) payloadMap.get("email");
            String username = (String) payloadMap.get("username");
            return LoggedInUser.builder()
                    .email(email)
                    .username(username)
                    .build();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}