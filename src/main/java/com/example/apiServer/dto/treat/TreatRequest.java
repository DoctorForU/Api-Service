package com.example.apiServer.dto.treat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatRequest { // 이거 만든 이유: 주민번호랑
    private String userIdentity; // 주민번호
    private String token; // access토큰

}
