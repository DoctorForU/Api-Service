package com.example.apiServer.dto.token;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String organizationName;
    private String organizationEmail;
}