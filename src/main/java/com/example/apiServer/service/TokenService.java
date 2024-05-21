package com.example.apiServer.service;

import com.example.apiServer.auth.jwt.JwtUtil;
import com.example.apiServer.dto.token.TokenResponse;
import com.example.apiServer.entity.Token;
import com.example.apiServer.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {
    private final JwtUtil jwtUtil;
    private final TokenRepository tokenRepository;

    public TokenResponse getRefreshToken(String userId){
        String refreshToken = jwtUtil.createRefreshToken(userId, Instant.now());
        tokenRepository.save(new Token(userId, refreshToken));
        return new TokenResponse(refreshToken);
    }
}