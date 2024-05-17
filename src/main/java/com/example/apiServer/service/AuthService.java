package com.example.apiServer.service;

import com.example.apiServer.auth.jwt.TokenProvider;
import com.example.apiServer.dto.user.UserResponse;
import com.example.apiServer.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public UserResponse authenticate(String userName, String userIdentity) {
        UsernamePasswordAuthenticationToken  authenticationToken = new UsernamePasswordAuthenticationToken(userName, userIdentity);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // Token 발급
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        return UserResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

//    public String recreateAccessToken(String accessToken, String refreshToken) {
//
//    }
}
