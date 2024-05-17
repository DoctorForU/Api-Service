package com.example.apiServer.dto.treat;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TreatResponse {
    Long id; //진료내역
    Long medicationId; //처방내역
    LocalDateTime treatStartDate; //진료개시일
    int treatSubject; //진료과목
    String hospitalName;
    String visitDays; //방문일수
    int prescribeCnt; //복용기간

    // builder ( 하나로 만들기 )

}
