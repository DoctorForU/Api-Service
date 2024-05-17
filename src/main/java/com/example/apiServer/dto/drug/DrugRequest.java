package com.example.apiServer.dto.drug;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrugRequest {
    String prescribeDrugName; //처방약품명
}
