package com.example.apiServer.controller;


import com.example.apiServer.dto.token.TokenRequest;
import com.example.apiServer.dto.treat.TreatRequest;
import com.example.apiServer.dto.treat.TreatResponse;
import com.example.apiServer.jwt.TokenProvider;
import com.example.apiServer.service.TreatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/treat")
public class TreatController {

    private final TreatService treatService;

    @Autowired
    private TokenProvider tokenProvider;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/getTreat") // data요청 ->
    //public ResponseEntity<TreatResponse> getTreat(@RequestBody TreatRequest treatRequest){
    public ResponseEntity<List<TreatResponse>> getTreat(@RequestBody TreatRequest treatRequest){
        logger.info("request userIdentity: " + treatRequest.getUserIdentity());
        logger.info("request startDate: " + treatRequest.getStartDate());
        logger.info("request endDate: " + treatRequest.getEndDate());
        logger.info("request token: " + treatRequest.getToken());

        String accessToken = treatRequest.getToken();

        //토큰 검증 -> 인증된 기관에서 온 요청인지 확인
        if (!tokenProvider.validateToken(accessToken)) {
            logger.warn("Invalid token: " + accessToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try{
            //TreatResponse treatResponse = treatService.getTreat(treatRequest);
            List<TreatResponse> treatResponses = treatService.getTreat(treatRequest);
            logger.info(String.valueOf(treatResponses));
            return ResponseEntity.ok(treatResponses);
        } catch(Exception e){
            logger.error("Error processing getTreat request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/getTreatAndDate") // data요청 ->
    //public ResponseEntity<TreatResponse> getTreat(@RequestBody TreatRequest treatRequest){
    public ResponseEntity<List<TreatResponse>> getTreatAndDate(@RequestBody TreatRequest treatRequest){

        logger.info("request userIdentity before - : " + treatRequest.getUserIdentity());
        String ChangeuserIdentity = treatRequest.getUserIdentity().replace("-", "");
        logger.info("request userIdentity after - : " + treatRequest.getUserIdentity());
        treatRequest.setUserIdentity(ChangeuserIdentity);
        logger.info("request startDate: " + treatRequest.getStartDate());
        logger.info("request endDate: " + treatRequest.getEndDate());
        logger.info("request token: " + treatRequest.getToken());

        String accessToken = treatRequest.getToken();

        //토큰 검증 -> 인증된 기관에서 온 요청인지 확인
        if (!tokenProvider.validateToken(accessToken)) {
            logger.warn("Invalid token: " + accessToken);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        try{
            //TreatResponse treatResponse = treatService.getTreat(treatRequest);
            List<TreatResponse> treatResponses = treatService.getTreat(treatRequest);
            logger.info(String.valueOf(treatResponses));
            return ResponseEntity.ok(treatResponses);
        } catch(Exception e){
            logger.error("Error processing getTreat request", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
