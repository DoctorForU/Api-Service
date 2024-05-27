package com.example.apiServer.controller;

import com.example.apiServer.dto.token.RefreshTokenRequest;
import com.example.apiServer.dto.token.TokenRequest;
import com.example.apiServer.dto.token.TokenResponse;
import com.example.apiServer.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/auth") // 기관 인증
    public ResponseEntity<TokenResponse> getAuth(@RequestBody TokenRequest tokenRequest) {
        TokenResponse token = tokenService.getAuthToken(tokenRequest.getOrganizationName(), tokenRequest.getOrganizationEmail());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenResponse> getRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        TokenResponse response = tokenService.getRefreshToken(refreshTokenRequest.getOrganizationName(), refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok("test");
    }
}
