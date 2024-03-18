package com.communitcation.rest.client;

import com.communitcation.rest.service.RestTemplateComponent;
import lombok.*;
import org.apache.hc.core5.http.Method;
import org.springframework.http.*;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommunicationInfo <T>{
    private String path; // api 경로
    private String scheme; // 스키마(http / https)
    private String host; // 주소
    private String port; // 포트
    private Method method; // http method
    private MediaType mediaType; // 미디어타입
    private T requestData; // 요청 데이터 문자열
    private Map<String,String> headers; // 추가적인 헤더
    private RequestFormat requestFormat; // 요청 데이터 형식
    private RestTemplateComponent restClient; // RestTemplate 객체
    private Class<?> responseClazz; // 응답 데이터 클래스 타입
}
