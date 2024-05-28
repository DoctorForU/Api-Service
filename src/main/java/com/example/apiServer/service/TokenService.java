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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class TokenService {
    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TokenResponse getAuthToken(String organizationId, String organizationEmail) {
        try {
            logger.debug("Authenticating organization with ID: " + organizationId + " and email: " + organizationEmail);

            // 권한 생성 (기관, 공식 메일)
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(organizationId, organizationEmail);
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            logger.debug("Authentication successful for organization ID: " + organizationId);

            // Token 발급
            String accessToken = tokenProvider.createAccessToken(authentication);
            String refreshToken = tokenProvider.createRefreshToken(authentication);

            logger.debug("Access and refresh tokens created for organization ID: " + organizationId);

            // 공식 이메일 - RefreshToken 저장
            tokenRepository.save(new Token(organizationEmail, refreshToken));

            logger.debug("Refresh token saved for organization email: " + organizationEmail);

            return new TokenResponse(accessToken, refreshToken);
        } catch (Exception e) {
            logger.error("Error occurred while generating auth token for organization ID: " + organizationId + " and email: " + organizationEmail, e);
            throw e; // 예외를 다시 던져서 트랜잭션 롤백이 일어나도록 함
        }
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