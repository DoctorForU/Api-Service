package com.example.apiServer.service;

import com.example.apiServer.entity.Treat;
import com.example.apiServer.repository.TreatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TreatService { // 진단서 -> 받은 request의 주민번호를 dto로 변형해서 -> 그 값을 가상 api-server에 주민번호와 일치 시켜 데이터를 response하기
    @Autowired
    private TreatRepository treatRepository;

    // 진료 내역
    public Optional<Treat> findById(Long id){
        return treatRepository.findById(id);
    }

//    public List<Treat> findAllByIdentity(String userIdentity){
//        return treatRepository.findAllByUserIdentity(userIdentity);
//    }
}
