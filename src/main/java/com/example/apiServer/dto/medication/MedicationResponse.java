package com.example.apiServer.dto.medication;

import com.example.apiServer.entity.Drug;
import com.example.apiServer.entity.Medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationResponse {
    String diseaseId; //질병분류
    int prescribeCnt; //복용기간
    List<Drug> drug; //약품내역
    LocalDateTime treatDate; //진료,처방일자

    public MedicationResponse(Medication byId) {
    }
}
