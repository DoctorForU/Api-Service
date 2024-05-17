package com.example.apiServer.controller;

import com.example.apiServer.auth.jwt.CustomJwtFilter;
import com.example.apiServer.dto.user.UserRequest;
import com.example.apiServer.dto.user.UserResponse;
import com.example.apiServer.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest) {
        UserResponse token = authService.authenticate(userRequest.getUserName(), userRequest.getUserIdentity());
        HttpHeaders headers = new HttpHeaders();
        headers.add(CustomJwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

        return ResponseEntity.ok()
                .headers(headers)
                .body(token);
    }

//    @PostMapping("/reissue")
//    public ResponseEntity<String> reissue(@RequestBody ReissueRequest reissueRequest){
//
//    }
}
