package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "treat")
@Data
@NoArgsConstructor
public class Treat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "treatId")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medicationId")
    private Medication medication; // 처방내역

    private LocalDateTime treatStartDate; // 진료개시일 -> date 객체 유무 알고 적어야 함 - date, timestamp

    private int treatSubject; //진료과목

    private String hospitalName;

    private String visitDays; //방문일수

    @Column(name = "userName", unique = false, nullable = false)
    private String userName; // 이름

    @Column(name = "userIdentity", unique = true, nullable = false)
    private String userIdentity; //주민번호

    private int prescribeCnt; //처방횟수
    private int deductibleAmt; //본인부담금
    private int publicCharge; //공단부담금
}