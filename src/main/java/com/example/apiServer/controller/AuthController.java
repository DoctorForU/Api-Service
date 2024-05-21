package com.example.apiServer.controller;

import com.example.apiServer.auth.jwt.CustomJwtFilter;
import com.example.apiServer.dto.token.TokenRequest;
import com.example.apiServer.dto.token.TokenResponse;
import com.example.apiServer.dto.user.UserRequest;
import com.example.apiServer.dto.user.UserResponse;
import com.example.apiServer.service.AuthService;
import com.example.apiServer.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final TokenService tokenService;

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest) {
        UserResponse token = authService.authenticate(userRequest.getUserId(), userRequest.getUserIdentity());
        HttpHeaders headers = new HttpHeaders();
        headers.add(CustomJwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

        return ResponseEntity.ok()
                .headers(headers)
                .body(token);
    }

    @GetMapping("/refresh/{id}")
    public ResponseEntity<TokenResponse> getRefresh(@PathVariable("id") Long id) {
        TokenResponse response = tokenService.getRefreshToken(id);
        return ResponseEntity.ok(response);
    }
}
