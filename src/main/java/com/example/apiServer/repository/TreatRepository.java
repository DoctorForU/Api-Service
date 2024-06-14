package com.example.apiServer.repository;

import com.example.apiServer.entity.Treat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface TreatRepository extends JpaRepository<Treat, Long> {
    //Optional<Treat> findByUserIdentity(String userIdentity);
    List<Treat> findByUserIdentity(String userIdentity);
}