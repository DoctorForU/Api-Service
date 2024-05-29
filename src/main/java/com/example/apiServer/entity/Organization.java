package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "organization")
@Data
@NoArgsConstructor
public class Organization { // 인증된 기관들을 저장해놓은 테이블
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organization_id")
    private Long id;
    @Column(name = "organization_name")
    private String name;
    @Column(name = "organization_email", unique = true)
    private String email;

}