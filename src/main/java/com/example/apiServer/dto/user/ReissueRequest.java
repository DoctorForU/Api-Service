package com.example.apiServer.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReissueRequest {
    String accessToken;
    String refreshToken;
}
