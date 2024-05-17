package com.example.apiServer.dto.drug;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrugResponse {
    String prescribeDrugName; //처방약품명
    String perscribeDrugEffect; //처방약품효능
}
