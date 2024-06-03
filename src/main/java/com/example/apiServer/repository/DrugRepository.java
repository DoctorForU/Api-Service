package com.example.apiServer.repository;

import com.example.apiServer.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DrugRepository extends JpaRepository<Drug, Long> {
}