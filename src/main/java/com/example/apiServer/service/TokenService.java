package com.example.apiServer.service;

import com.example.apiServer.api.status.ErrorStatus;
import com.example.apiServer.jwt.TokenProvider;
import com.example.apiServer.dto.token.TokenResponse;
import com.example.apiServer.entity.Organization;
import com.example.apiServer.entity.Token;
import com.example.apiServer.exception.GeneralException;
import com.example.apiServer.repository.OrganizationRepository;
import com.example.apiServer.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class TokenService {
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public TokenResponse getAuthToken(String organizationId, String organizationEmail) {
        // 권한 생성 (기관, 공식 메일)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(organizationId, organizationEmail);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // Token 발급
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        // 공식 이메일 - RefreshToken 저장
        tokenRepository.save(new Token(organizationEmail, refreshToken));
        return new TokenResponse(accessToken, refreshToken);
    }

    public TokenResponse getRefreshToken(String organizationName, String refreshToken){
        String organizationEmail = findByOrganizationName(organizationName);
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);

        // Token 발급
        String newAccessToken = tokenProvider.createAccessToken(authentication);
        String newRefreshToken = tokenProvider.createRefreshToken(authentication);

        // 공식 이메일 - RefreshToken 저장
        tokenRepository.save(new Token(organizationEmail, refreshToken));
        return new TokenResponse(newAccessToken, newRefreshToken);
    }

    public String findByOrganizationName(String organizationName){
        return organizationRepository.findByOrganizationName(organizationName).map(Organization::getOrganizationEmail)
                .orElseThrow(()->new GeneralException(ErrorStatus._USER_NOT_FOUND));
    }
}