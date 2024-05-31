package com.example.apiServer.dto.token;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String organizationName; // DoctorForU
    private String organizationEmail; // uj0791naver.com@skuniv.ac.kr
}