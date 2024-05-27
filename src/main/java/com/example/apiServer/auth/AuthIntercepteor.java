package com.example.apiServer.auth;

import com.example.apiServer.api.status.ErrorStatus;
import com.example.apiServer.exception.GeneralException;
import com.example.apiServer.jwt.JwtInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthIntercepter implements HandlerInterceptor {

    private final JwtInterceptor jwtInterceptor;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if(request.getHeader(HttpHeaders.AUTHORIZATION)==null){
            throw new GeneralException(ErrorStatus._UNAUTHORIZED);
        }

        jwtInterceptor.preHandle(request, response, handler);

        return true;
    }
}