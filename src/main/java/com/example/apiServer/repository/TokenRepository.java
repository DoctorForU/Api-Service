package com.example.apiServer.repository;

import com.example.apiServer.entity.Token;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
    Optional<Token> findByToken(String token);

    Optional<Token> findByAuthId(String authId);
}