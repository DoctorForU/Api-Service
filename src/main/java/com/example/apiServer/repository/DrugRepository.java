package com.example.apiServer.repository;

import com.example.apiServer.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DrugRepository extends JpaRepository<Drug, String> {
    Optional<Drug> findById(String id);
}