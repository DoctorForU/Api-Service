package com.example.apiServer.service;

import com.example.apiServer.dto.medication.MedicationResponse;
import com.example.apiServer.dto.treat.TreatRequest;
import com.example.apiServer.dto.treat.TreatResponse;
import com.example.apiServer.entity.Token;
import com.example.apiServer.entity.Treat;
import com.example.apiServer.jwt.TokenProvider;
import com.example.apiServer.repository.MedicationRepository;
import com.example.apiServer.repository.TreatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TreatService { // 진단서 -> 받은 request의 주민번호를 dto로 변형해서 -> 그 값을 가상 api-server에 주민번호와 일치 시켜 데이터를 response하기
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TreatRepository treatRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    @Autowired
    private TokenProvider tokenProvider;

    public TreatResponse getTreat(TreatRequest treatRequest) {
        Optional<Treat> optionalTreat = treatRepository.findByUserIdentity(treatRequest.getUserIdentity());
        if (optionalTreat.isPresent()) {
            Treat treat = optionalTreat.get();
            return convertToDto(treat);
        } else {
            throw new RuntimeException("Treat not found");
        }
    }

    public TreatResponse convertToDto(Treat treat) {
        MedicationResponse medicationResponse = new MedicationResponse(treat.getMedication());

        return TreatResponse.builder()
                .id(treat.getId())
                .medicationId(medicationResponse)
                .treatStartDate(treat.getStartDate())
                .treatSubject(treat.getSubject())
                .hospitalName(treat.getHospitalName())
                .hpid(treat.getHpid())
                .visitDays(treat.getVisitDays())
                .userName(treat.getUserName())
                .userIdentity(treat.getUserIdentity())
                .prescribeCnt(treat.getPrescribeCnt())
                .deductibleAmt(treat.getDeductibleAmt())
                .publicCharge(treat.getPublicCharge())
                .build();
    }
}


