package com.example.apiServer.service;

import com.example.apiServer.auth.jwt.TokenProvider;
import com.example.apiServer.dto.token.RefreshTokenResponse;
import com.example.apiServer.dto.token.TokenResponse;
import com.example.apiServer.entity.Token;
import com.example.apiServer.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public TokenResponse getAuthToken(String userId, String userRole) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userId, userRole);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // Token 발급
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        tokenRepository.save(new Token(userId, refreshToken));
        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse getRefreshToken(String userId, String refreshToken){
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        String newRefreshToken = tokenProvider.createRefreshToken(authentication);
        tokenRepository.save(new Token(userId, newRefreshToken));
        return new TokenResponse(refreshToken);
    }
}