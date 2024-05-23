package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organization")
@Getter @Setter
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "organizationId")
    private String id; // 기관명
    private String email;

}