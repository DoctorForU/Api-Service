package com.example.apiServer.auth.jwt;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomJwtFilter implements Filter {
    public static final String AUTHORIZATION_HEADER = "Authorization";

    private final TokenProvider tokenProvider;

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String jwt = resolveToken(req);
        String requestURI = req.getRequestURI();

        // 토큰 유효성 검사
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) { // 토큰에 이상이 없는 경우
            // 토큰에서 사용자명, 권한을 추출하여 스프링 시큐리티 사용자를 만들어 Authentication 반환
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("Security Context에 %s 인증 정보를 저장했습니다. URI : %s", authentication.getName(), requestURI);
        } else {
            log.debug("유효한 JWT 토큰이 없습니다. URI: %s", requestURI);
        }

        //다음 필터로 넘기기
        chain.doFilter(request, response);
    }

    /**
     * HttpServletRequest에서 `Authorization` 헤더를 받음.
     * 헤더에서 'Bearer'로 시작하는 토큰이 있으면 'Bearer' 부분 제거하고 토큰 값 반환 아니면 널 값 반환
     * @param request
     * @return
     */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
