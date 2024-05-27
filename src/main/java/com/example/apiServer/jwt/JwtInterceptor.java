package com.example.apiServer.jwt;

import com.example.apiServer.config.JwtConfig;
import com.example.apiServer.exception.GeneralException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class JwtInterceptor implements HandlerInterceptor {
    private final JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!jwtConfig.isIncludeTokenPrefix(header)) {
            throw new GeneralException(ErrorStatus._NO_BEARER_PREFIX);
        }

        String token = jwtConfig.extractTokenFromAuthorization(header);
        if (jwtConfig.isTokenExpired(token)) {
            throw new GeneralException(ErrorStatus._ACCESS_TOKEN_EXPIRED);
        }

        if (!jwtConfig.isTokenNotManipulated(token)) {
            throw new GeneralException(ErrorStatus._TOKEN_SIGNATURE_NOT_VALID);
        }

        return true;
    }
}