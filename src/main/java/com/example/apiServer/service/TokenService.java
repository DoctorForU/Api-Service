package com.example.apiServer.service;

import com.example.apiServer.api.status.ErrorStatus;
import com.example.apiServer.dto.token.AccessTokenResponse;
import com.example.apiServer.jwt.TokenProvider;
import com.example.apiServer.dto.token.TokenResponse;
import com.example.apiServer.entity.Organization;
import com.example.apiServer.entity.Token;
import com.example.apiServer.exception.GeneralException;
import com.example.apiServer.repository.OrganizationRepository;
//import com.example.apiServer.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenProvider tokenProvider;
    // private final TokenRepository tokenRepository;
    private final OrganizationRepository organizationRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TokenResponse getAuthToken(String organizationName, String organizationEmail) {
        try {
            logger.debug("Authenticating organization with ID: " + organizationName + " and email: " + organizationEmail);

            // 권한 생성 (기관, 공식 메일)
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(organizationName, organizationEmail);
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

            Long created_At = new Date().getTime();

            // Token 발급
            String accessToken = tokenProvider.createAccessToken(authentication, created_At);
            String refreshToken = tokenProvider.createRefreshToken(authentication, created_At);

            // RefreshToken 저장
            //tokenRepository.save(new Token(organizationName, refreshToken, created_At));

            return new TokenResponse(accessToken, refreshToken);
        } catch (Exception e) {
            logger.error("Error occurred while generating auth token for organization ID: " + organizationName + " and email: " + organizationEmail, e);
            throw new GeneralException(ErrorStatus._UNKNOWN); // 예외를 다시 던져서 트랜잭션 롤백이 일어나도록 함
        }
    }

    public AccessTokenResponse getAccessToken(String organizationName, String refreshToken) {
        //findByOrganizationName(organizationName);
        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        Long nowTime = new Date().getTime();

        // AccessToken 발급
        String newAccessToken = tokenProvider.createAccessToken(authentication, nowTime);

        return new AccessTokenResponse(newAccessToken);
    }

//    public Organization findByOrganizationName(String organizationName) {
//        return organizationRepository.findByOrganizationName(organizationName)
//                .orElseThrow(() -> new GeneralException(ErrorStatus._USER_NOT_FOUND));
//    }
}
