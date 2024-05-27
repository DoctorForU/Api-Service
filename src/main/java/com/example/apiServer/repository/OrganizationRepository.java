package com.example.apiServer.repository;

import com.example.apiServer.entity.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, Long>{
    Optional<Organization> findByOrganizationName(String organizationName);
}