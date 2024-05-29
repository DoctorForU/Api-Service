package com.example.apiServer.repository;

import com.example.apiServer.entity.Token;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Long> {
//    Optional<Token> findById(Long id);
//
//    Optional<Token> findByToken(String token);
//
//    int deleteByOrganizationName(String organizationName);
}
