package com.sparta.springauth.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j(topic = "LoggingFilter")
//@Component
@Order(1) // 필터 체인에서의 순서를 지정할 수 있다.
public class LoggingFilter implements Filter { // 필터 인터페이스를 구현
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 전처리
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String url = httpServletRequest.getRequestURI();
        log.info(url);

        chain.doFilter(request, response); // 다음 Filter 로 이동

        // 후처리 (응답이 완료된 이후에 실행됨)
        log.info("비즈니스 로직 완료");
    }
}