package com.example.apiServer.service;

import com.example.apiServer.entity.Drug;
import com.example.apiServer.entity.Organization;
import com.example.apiServer.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Organization findById(Long id){
        Optional<Organization> organization = organizationRepository.findById(id);
        return organization.get();
    }

    @Transactional
    public Organization findByorganizationName(String organizationName){
        Optional<Organization> organization2 = organizationRepository.findByOrganizationName(organizationName);
        return organization2.get();
    }

    public Organization registerOrganization(String organizationName, String organizationEmail, String rawPassword) {
        Organization organization = new Organization();
        organization.setOrganizationName(organizationName);
        organization.setOrganizationEmail(organizationEmail);
        organization.setPassword(passwordEncoder.encode(rawPassword)); // 비밀번호를 암호화하여 설정

        return organizationRepository.save(organization);
    }


}
