package com.example.apiServer.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@RedisHash(value = "token", timeToLive = 10)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Token {
    @Id
    private String id;
    private String refreshToken;
}