package com.example.apiServer.dto.organization;

import lombok.Data;

@Data
public class OrganizationRegistrationRequest {
    private String organizationName;
    private String organizationEmail;
    private String password;
}

