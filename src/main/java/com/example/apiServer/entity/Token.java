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
    @Column(name = "id")
    private Long id;

    @Column(name = "organization_name", nullable = false)
    private String organizationName; // 기관 이름

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Long createdAt;

    public Token(String organizationName, String token, Long createdAt) {
        this.organizationName = organizationName;
        this.token = token;
        this.createdAt = createdAt;
    }

    public Token update(String token) {
        this.token = token;
        return this;
    }
}
