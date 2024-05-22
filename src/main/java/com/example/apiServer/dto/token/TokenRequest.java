package com.example.apiServer.dto.token;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {
    private String userId;
    private String userRole;
}