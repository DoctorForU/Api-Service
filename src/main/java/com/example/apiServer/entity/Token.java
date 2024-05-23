package com.example.apiServer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@RedisHash(value = "token", timeToLive = 2592000) // 30 days in seconds // 추가설명 필요
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Token { // Reddis를 사용해 리프레시토큰 관리하기
    @Id
    private String id; // 직접 넣어야 함

    private String refreshToken;

}