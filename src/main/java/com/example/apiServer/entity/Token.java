package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "token")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter @Setter
public class Token {
    @Id
    private String userId;
    private String refreshToken;
}
