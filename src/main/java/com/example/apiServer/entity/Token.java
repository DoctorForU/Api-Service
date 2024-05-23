package com.example.apiServer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Optional;

@RedisHash(value = "token", timeToLive = 2592000) // 30 days in seconds
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Token {
    @Id
    private String id;
    private String refreshToken;

}