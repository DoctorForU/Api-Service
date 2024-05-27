package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organization")
@Getter @Setter
public class Organization { // 인증된 기관들을 저장해놓은 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organizationId")
    private Long id;
    private String organizationName;

    private String organizationEmail;

}