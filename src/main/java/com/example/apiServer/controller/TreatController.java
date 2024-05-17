package com.example.apiServer.controller;

import com.example.apiServer.dto.medication.MedicationRequest;
import com.example.apiServer.dto.medication.MedicationResponse;
import com.example.apiServer.dto.user.UserRequest;
import com.example.apiServer.entity.Treat;
import com.example.apiServer.service.MedicationService;
import com.example.apiServer.service.TreatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/treat")
@RequiredArgsConstructor
public class TreatController {
    @Autowired
    private TreatService treatService;
    @Autowired
    private MedicationService medicationService;

    @PostMapping("/")
    public ResponseEntity<List<Treat>> treat(@RequestBody UserRequest userRequest){
        List<Treat> treat = treatService.findAllByIdentity(userRequest.getUserIdentity());
        return ResponseEntity.ok()
                .body(treat);
    }

    @PostMapping("/medication")
    public ResponseEntity<MedicationResponse> medication(@RequestBody MedicationRequest medicationRequest){
        MedicationResponse medication = new MedicationResponse(medicationService.findById(medicationRequest.getId()));
        return ResponseEntity.ok()
                .body(medication);
    }
}
