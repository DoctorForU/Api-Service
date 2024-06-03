package com.example.apiServer.dto.medication;

import com.example.apiServer.dto.drug.DrugResponse;
import com.example.apiServer.entity.Drug;
import com.example.apiServer.entity.Medication;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationResponse {
    private Long id; // 처방전 번호
    private String diseaseId; // 질병분류
    private int prescribeDays;
    private LocalDate treatDate;
    private List<DrugResponse> drugs;


    public MedicationResponse(Medication medication) {
        this.id = medication.getId();
        this.diseaseId = medication.getDiseaseId();
        this.prescribeDays = medication.getPrescribeDays();
        this.treatDate = medication.getTreatDate();
        this.drugs = medication.getDrugs().stream()
                .map(DrugResponse::new)
                .collect(Collectors.toList());
    }


}
