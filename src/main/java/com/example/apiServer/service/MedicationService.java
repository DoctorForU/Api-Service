package com.example.apiServer.service;

import com.example.apiServer.entity.Medication;
import com.example.apiServer.repository.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepository;

    // 처방 정보
    @Transactional
    public Medication findById(Long id){
        Optional<Medication> medication = medicationRepository.findById(id);
        return medication.get();
    }
}
