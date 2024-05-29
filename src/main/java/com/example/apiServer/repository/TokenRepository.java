package com.example.apiServer.repository;

import com.example.apiServer.entity.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface TokenRepository extends CrudRepository<Token, Long> {
    Optional<Token> findById(Long id);

    Optional<Token> findByRefreshToken(String refreshToken);

    @Transactional
    int deleteByOrganizationName(String organizationName);
}
