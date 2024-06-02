package com.example.apiServer.controller;

import com.example.apiServer.dto.organization.OrganizationRegistrationRequest;
import com.example.apiServer.entity.Organization;
import com.example.apiServer.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/register") // 기관 등록
    public ResponseEntity<Organization> registerOrganization(@RequestBody OrganizationRegistrationRequest request) {
        logger.info("request -  organizationName: " + request.getOrganizationName());
        logger.info("request -  organizationEmail: " + request.getOrganizationEmail());
        logger.info("request -  organizationPassword: " + request.getPassword());

        Organization organization = organizationService.registerOrganization(
                request.getOrganizationName(),
                request.getOrganizationEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok(organization);
    }

    @PostMapping("/organization") // 기관 인증
    public ResponseEntity<Organization> getOrganization(@RequestBody Long organizationId) {
        Organization response =  organizationService.findById(organizationId);
        logger.info("response access: " + response.getId() +  " " +response.getOrganizationEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/organization2") // 기관 인증
    public ResponseEntity<Organization> getOrganization2(@RequestBody String organizationName) {
        Organization response =  organizationService.findByorganizationName(organizationName);
        logger.info("response access: " + response.getId() +  " " +response.getOrganizationEmail());
        return ResponseEntity.ok(response);
    }
}
