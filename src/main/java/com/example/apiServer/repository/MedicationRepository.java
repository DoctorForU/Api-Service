package com.example.apiServer.repository;

import com.example.apiServer.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    @Transactional(readOnly = true)
    Optional<Medication> findById(Long id);
}
