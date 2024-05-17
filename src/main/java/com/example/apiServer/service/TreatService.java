package com.example.apiServer.service;

import com.example.apiServer.entity.Treat;
import com.example.apiServer.repository.TreatRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatService {
    @Autowired
    private TreatRepository treatRepository;

    // 진료 내역
    @Transactional
    public Optional<Treat> findById(Long id){
        return treatRepository.findById(id);
    }

    @Transactional
    public List<Treat> findAllByIdentity(String userIdentity){
        return treatRepository.findAllByUserIdentity(userIdentity);
    }
}
