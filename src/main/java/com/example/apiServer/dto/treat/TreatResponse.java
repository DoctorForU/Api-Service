package com.example.apiServer.dto.treat;

import com.example.apiServer.dto.medication.MedicationResponse;
import com.example.apiServer.entity.Medication;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatResponse {
    private Long id; //진료내역
    private MedicationResponse medicationId; //처방내역
    private LocalDate treatStartDate; //진료개시일
    private int treatSubject; //진료과목
    private String hospitalName; // 병원이름
    private String hpid; // 병원 코드 -> 실데이터 A1113478
    private String visitDays; //방문일수
    private String userName;
    private int prescribeCnt; //처방횟수
    private String userIdentity; // 주민번호
    private int deductibleAmt; //본인부담금
    private int publicCharge; //공단부담금

    // builder ( 하나로 만들기 )

}
