package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medication")
@Data
@NoArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 일단 이거 추가
    @Column(name = "medicationId")
    private Long id;

    @OneToOne(mappedBy = "medication", fetch = FetchType.LAZY)
    private Treat treat;

    private String diseaseId; // 질병분류

    private int prescribeDays; // 복용기간

    private Date treatDate; // 진료, 처방일자
}