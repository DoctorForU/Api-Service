package com.example.apiServer.controller;


import com.example.apiServer.dto.medication.MedicationRequest;
import com.example.apiServer.dto.medication.MedicationResponse;
import com.example.apiServer.dto.treat.TreatRequest;
import com.example.apiServer.dto.treat.TreatResponse;
import com.example.apiServer.entity.Medication;
import com.example.apiServer.entity.Treat;
import com.example.apiServer.jwt.TokenProvider;
import com.example.apiServer.service.MedicationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/medication")
public class MedicationController {

    //private final TokenProvider tokenProvider;

    private final MedicationService medicationService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/{id}") // 처방전 번호를 보내줘야 함 FE에서
    public ResponseEntity<MedicationResponse> getMedication(@PathVariable Long id) {
        try {
            Medication medication = medicationService.getMedication(id);
            MedicationResponse response = medicationService.convertToMedicationDto(medication);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



}
