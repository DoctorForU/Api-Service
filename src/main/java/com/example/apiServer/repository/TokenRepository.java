package com.example.apiServer.repository;

import com.example.apiServer.entity.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
}