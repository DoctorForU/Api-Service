package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "drug")
@Data
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drugId")
    private Long drugCode; //약품코드

    private String prescribeDrugName; //처방약품명

    private String prescribeDrugEffect; //처방약품효능
}