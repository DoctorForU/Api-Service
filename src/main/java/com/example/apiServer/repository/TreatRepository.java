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
    Optional<Treat> findById(Long id);
    @Query("select tr from Treat tr where tr.userIdentity = :userIdentity")
    List<Treat> findAllByUserIdentity(@Param("userIdentity") String userIdentity); // 이게 주민 번호와 일치시키는 것
}