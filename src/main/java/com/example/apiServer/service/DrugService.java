package com.example.apiServer.service;

import com.example.apiServer.entity.Drug;
import com.example.apiServer.repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;

    // 약물 정보
    @Transactional
    public Drug findById(String id){
        Optional<Drug> drug = drugRepository.findById(id);
        return drug.get();
    }
}
