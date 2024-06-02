package com.example.apiServer.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.apiServer.entity.Organization;
import com.example.apiServer.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final OrganizationRepository organizationRepository;

    @Override
    public UserDetails loadUserByUsername(String organizationName) throws UsernameNotFoundException {
        Organization organization = organizationRepository.findByOrganizationName(organizationName)
                .orElseThrow(() -> new UsernameNotFoundException("Organization not found: " + organizationName));

        return User.builder()
                .username(organization.getOrganizationName())
                .password(organization.getPassword()) // Assuming password is stored
                .roles("USER") // or other roles as needed
                .build();
    }
}
