package com.example.apiServer.repository;

import java.util.Optional;
import com.example.apiServer.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByUserId(Long authId);
}