package com.example.apiServer.dto.drug;

import com.example.apiServer.entity.Drug;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrugResponse {
    Long id;
    Long code; //약품코드
    String name; //처방약품명
    String effect; //처방약품효능

    public DrugResponse(Drug drug){
        this.id = drug.getId();
        this.code = drug.getCode();
        this.name = drug.getName();
        this.effect = drug.getEffect();
    }



}
