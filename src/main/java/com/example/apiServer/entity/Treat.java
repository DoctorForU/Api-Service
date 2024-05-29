package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "treat")
@Data
@NoArgsConstructor
public class Treat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키를 자동으로 1씩 증가
    @Column(name = "treat_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "treat_id")
    private Medication medication; // 처방내역

    @Column(name = "treat_startDate", nullable = false)
    private Date startDate; // 진료개시일 -> date 객체 유무 알고 적어야 함 - date, timestamp

    @Column(name = "treat_subject", nullable = false)
    private int subject; //진료과목

    @Column(name = "treat_hospitalName", nullable = false)
    private String hospitalName;

    @Column(name = "treat_visitDays")
    private String visitDays; //방문일수

    @Column(name = "treat_userName", nullable = false)
    private String userName; // 이름

    @Column(name = "treat_userIdentity", unique = true, nullable = false)
    private String userIdentity; //주민번호

    @Column(name = "treat_prescribeCnt")
    private int prescribeCnt; //처방횟수

    @Column(name = "treat_deductibleAmt")
    private int deductibleAmt; //본인부담금

    @Column(name = "treat_publicCharge")
    private int publicCharge; //공단부담금
}