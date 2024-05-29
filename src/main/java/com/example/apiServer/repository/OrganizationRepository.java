package com.example.apiServer.repository;

import com.example.apiServer.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
//    Optional<Organization> findByOrganizationName(String organizationName);
}