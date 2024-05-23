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

    @PostMapping("/auth")
    public ResponseEntity<TokenResponse> getAuth(@RequestBody TokenRequest tokenRequest) { // 여기서 주민번호를 받아
        TokenResponse token = tokenService.getAuthToken(tokenRequest.getUserId(), tokenRequest.getUserRole());
        return ResponseEntity.ok(token);
    }

    @GetMapping("/refresh")
    public ResponseEntity<TokenResponse> getRefresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        TokenResponse response = tokenService.getRefreshToken(refreshTokenRequest.getUserId(), refreshTokenRequest.getRefreshToken());
        return ResponseEntity.ok(response);
    }
}
