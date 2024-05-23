package com.example.apiServer.repository;

import com.example.apiServer.entity.Treat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TreatRepository extends JpaRepository<Treat, Long> {
    Optional<Treat> findById(Long id);
    List<Treat> findAllByUserIdentity(String userIdentity); // 이게 주민 번호와 일치시키는 것
}