package com.example.apiServer.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "organization")
@Getter @Setter
public class Organization { // 인증된 기관에서만 인가를 해줘야하니까 -> 그 기관들을 저장해놓은 테이블
    // 닥터포유가 들어갈 거임 ㅋㅋ -> 핸드폰이나 이메일로 인증을 해야함 ( 그 인증을 처리할 수 있는 공인된 기관이 우린 닥터포유인것 )
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "organizationId")
    private String id; // 기관명

    private String email;


    /*
    * 1. 이메일 -> 우리 웹사이트 회원가입할 때 위 인증을 처리해야 함
    * 2. 전화번호인증
    * 3.
    *
    * 클라이언트 (어디서 mypage에 진단내역 page에 주민번호를 적어서 날려)
    * 가상 api-server
    * */

}