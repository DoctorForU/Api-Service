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
    @Column(name = "drug_id")
    private Long id;

    @Column(name = "drug_code", nullable = false)
    private Long code; //약품코드

    @Column(name = "drug_name", nullable = false)
    private String name; //처방약품명

    @Column(name = "drug_effect")
    private String effect; //처방약품효능
}