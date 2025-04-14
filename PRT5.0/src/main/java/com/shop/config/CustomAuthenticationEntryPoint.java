package com.shop.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        // 인증되지 않은 사용자가 XMLHttpRequest로 ajax로 리소스를 요청할 경우
        // "Unautorized"에러를 발생시킴
        // 나머지의 경우는 로그인 페이지로 리다이렉트 시킴
//        if("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//        } else {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

}