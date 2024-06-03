package com.example.apiServer.controller;

import com.example.apiServer.dto.token.AccessTokenResponse;
import com.example.apiServer.dto.token.RefreshTokenRequest;
import com.example.apiServer.dto.token.TokenRequest;
import com.example.apiServer.dto.token.TokenResponse;
import com.example.apiServer.service.TokenService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/auth") // 기관 인증
    public ResponseEntity<TokenResponse> getAuth(@RequestBody TokenRequest tokenRequest) {
        logger.info("request email: " + tokenRequest.getOrganizationEmail());
        logger.info("request name: " + tokenRequest.getOrganizationName());
        logger.info("request password: " + tokenRequest.getPassword());

        try {
            TokenResponse token = tokenService.getAuthToken(tokenRequest.getOrganizationName(), tokenRequest.getOrganizationEmail(), tokenRequest.getPassword());

            logger.info("response access: " + token.getAccessToken());
            logger.info("response refresh: " + token.getRefreshToken());

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            logger.error("Error occurred while getting auth token", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/access")
    public ResponseEntity<AccessTokenResponse> getRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        AccessTokenResponse response = tokenService.getAccessToken(refreshTokenRequest.getOrganizationName(), refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(response);
    }

}
