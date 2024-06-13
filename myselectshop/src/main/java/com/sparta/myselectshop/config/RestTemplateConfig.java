package com.sparta.myselectshop.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    // restTemplate은 옵션을 선택해 주는 경우가 많아서 수동으로 빈 등록을 해주는 경우가 많다.
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder
                .setConnectTimeout(Duration.ofSeconds(5)) // restTemplate으로 외부 API를 호출 했을 때 5초가 지나도 응답이 없으면 타임아웃 강제종료
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

}
