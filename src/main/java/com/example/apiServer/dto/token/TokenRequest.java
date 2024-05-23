package com.example.apiServer.dto.token;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String organizationId;
    private String organizationEmail;
}