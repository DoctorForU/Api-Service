package com.example.apiServer.repository;

import com.example.apiServer.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    @Transactional(readOnly = true)
    Optional<Organization> findByName(String organizationName);
}