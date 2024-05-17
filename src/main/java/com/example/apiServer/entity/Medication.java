package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medication")
@Getter @Setter
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @JoinColumn(name = "medicationId")
    private Long id;
    @OneToOne(mappedBy = "medication", fetch = FetchType.LAZY)
    private Treat treat;
    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL)
    private List<Drug> drugs = new ArrayList<>();
    private String diseaseId; //질병분류
    private int prescribeDays; //복용기간
    private Date treatDate; //진료,처방일자
}
