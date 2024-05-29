package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "token")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
    public class Token {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "token_id")
        private Long id;

        @Column(name = "token_organizationName", nullable = false)
        private String organizationName; // 기관 이름

        @Column(name = "token_refreshToken", nullable = false, unique = true)
        private String refreshToken;

        @Column(name = "token_createdAt", nullable = false)
    private Long createdAt;

    public Token(String organizationName, String refreshToken, Long createdAt) {
        this.organizationName = organizationName;
        this.refreshToken = refreshToken;
        this.createdAt = createdAt;
    }

    public Token update(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }
}
