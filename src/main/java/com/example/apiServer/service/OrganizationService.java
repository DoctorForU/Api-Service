package com.example.apiServer.service;

import com.example.apiServer.entity.Drug;
import com.example.apiServer.entity.Organization;
import com.example.apiServer.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional
    public Organization findById(Long id){
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.get();
    }

    @Transactional
    public Organization findByorganizationName(String organizationName){
        Optional<Organization> organization2 = organizationRepository.findByName(organizationName);
        return organization2.get();
    }
}
