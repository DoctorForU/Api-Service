package com.example.apiServer.repository;

import java.util.Optional;

import com.example.apiServer.entity.Organization;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<Organization, String>{
    Optional<String> findEmailByOrganizationId(String organizationId);
}