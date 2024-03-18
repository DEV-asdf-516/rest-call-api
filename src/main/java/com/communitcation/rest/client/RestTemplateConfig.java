package com.communitcation.rest.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    private Duration duration = Duration.ofSeconds(60);

    @Bean
    public RestTemplate restTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setConnectionRequestTimeout(duration); // 요청 시간
        httpRequestFactory.setConnectTimeout(duration); // tcp 연결 시간
        restTemplate.setRequestFactory(httpRequestFactory);
        return restTemplate;
    }
}
